package br.unb.cic.sma.sade.test

import br.unb.cic.sma.sade.fipa._
import br.unb.cic.sma.sade.agent._
import akka.actor._
import scala.collection.immutable.HashMap
import br.unb.cic.sma.sade.fipa.Performative

case object StartMessage
case object StopMessage
case object Done

class Ping(pong: ActorRef) extends Agent {
  var count = 0
  def incrementAndPrint { count += 1 }
  def receive = {
    case StartMessage =>
      incrementAndPrint
      val startMsg = new ACLMessage(Map((ACLMessageParameter.PERFORMATIVE -> Performative.REQUEST),
          (ACLMessageParameter.SENDER -> this.self), (ACLMessageParameter.RECEIVER -> pong),
          (ACLMessageParameter.CONTENT -> "PING TEST START")))
      pong ! startMsg
    case msg: ACLMessage =>
      incrementAndPrint
      if (count > 10) {
        //sender ! StopMessage
        context.actorSelection("../ripper") ! Done
      } else {
        println(msg.performative + ":" + msg.content + ":" + msg.sender)
        val message = new ACLMessage(Map((ACLMessageParameter.PERFORMATIVE -> Performative.REQUEST),
          (ACLMessageParameter.SENDER -> this.self),
          (ACLMessageParameter.RECEIVER -> sender),
          (ACLMessageParameter.CONTENT -> ("PING TEST " + count.toString))))
        sender ! message
      }
    case StopMessage =>
      println("ping stopped")
      context.stop(self)

  }
}

class Pong extends Agent {
  def receive = {
    case msg: ACLMessage =>
      println(msg.performative + ":" + msg.content + ":" + msg.sender)
      val reply = msg.reply(Performative.INFORM, "  PONG TEST")
      sender ! reply
    case StopMessage =>
      println("pong stopped")
      context.stop(self)

  }
}

class Ripper(system: ActorSystem, souls: List[ActorRef]) extends Agent {
  def receive = {
    case Done =>
      souls.foreach { soul => soul ! StopMessage }
      system.shutdown()
  }
}

object TestMAS extends App {
  val system = ActorSystem("PingPongSystem")
  val pong = system.actorOf(Props[Pong], name = "pong")
  val ping = system.actorOf(Props(new Ping(pong)), name = "ping")
  val souls = List[ActorRef](ping, pong)
  val ripper = system.actorOf(Props(new Ripper(system, souls)), name = "ripper")
  // start them going
  ping ! StartMessage
}