package ag.bett.scala.geoip.akka

import akka.actor._
import akka.kernel._
import akka.event.Logging
import akka.util._
import akka.util.duration._
import akka.remote._

import com.maxmind.geoip._


class GeoIPKernel extends Bootable {
	val system = ActorSystem("GeoIP")

	def startup = {
		val mgmt = system.actorOf(Props[GeoIPActor], "query")
		system.eventStream.subscribe(mgmt, classOf[RemoteLifeCycleEvent])
	}

	def shutdown = {
		system.shutdown()
	}

}


/* Query */
sealed trait GeoIPCountryQuery { val ip: String }
case class GeoIPCountryV4(ip: String) extends GeoIPCountryQuery
case class GeoIPCountryV6(ip: String) extends GeoIPCountryQuery

/* Result */
case class GeoIPCountry(ip: String, code: String, name: String)

class GeoIPActor extends Actor {

	protected val logger = Logging(context.system, this)
	protected val dbDirectory = "/usr/local/share/GeoIP/"
	protected val v4 = new LookupService(dbDirectory + "/GeoIP.dat", LookupService.GEOIP_MEMORY_CACHE);
	protected val v6 = new LookupService(dbDirectory + "/GeoIPv6.dat", LookupService.GEOIP_MEMORY_CACHE);

	def receive = {
		case a: String =>
			val msg = if (a.matches(":")) GeoIPCountryV6(a) else GeoIPCountryV4(a)
			self.forward(msg) 
		case a: GeoIPCountryV4 =>
			val result = v4.getCountry(a.ip)
			sender ! GeoIPCountry(a.ip, result.getCode, result.getName)
		case a: GeoIPCountryV6 =>
			val result = v6.getCountryV6(a.ip)
			sender ! GeoIPCountry(a.ip, result.getCode, result.getName)
	}

}


