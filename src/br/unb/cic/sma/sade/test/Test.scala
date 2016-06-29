package br.unb.cic.sma.sade.test

import br.unb.cic.sma.sade.fipa._
import akka.actor._
import scala.collection.immutable.HashMap
import br.unb.cic.sma.sade.fipa.Performative

case object PingMessage
case object PongMessage
case object StartMessage
case object StopMessage

class Ping(pong: ActorRef) extends Actor {
  var count = 0
  def incrementAndPrint { count += 1; println("ping") }
  def receive = {
    case StartMessage =>
        incrementAndPrint
        pong ! PingMessage
    case PongMessage => 
        incrementAndPrint
        if (count > 10) {
          sender ! StopMessage
          println("ping stopped")
          context.stop(self)
        } else {
          sender ! PingMessage
        }
    case msg: ACLMessage =>
        println(msg.parameters.get(ACLMessageParameter.PERFORMATIVE).orNull + ":" + msg.parameters.get(ACLMessageParameter.CONTENT).orNull)
        val reply = msg.reply(Performative.INFORM, "  PONG TEST")
        val sendTo = reply.parameters.get(ACLMessageParameter.REPLY_TO).orNull.asInstanceOf[ActorRef]
        reply.sender ! reply        
  }
}

class Pong extends Actor {
  def receive = {
    case PingMessage =>
        println("  pong")
        val message = ACLMessage(HashMap((ACLMessageParameter.PERFORMATIVE -> Performative.REQUEST),
                      (ACLMessageParameter.SENDER -> this.sender()),
                      (ACLMessageParameter.RECEIVER -> sender),
                      (ACLMessageParameter.CONTENT -> "PING TEST")))
        sender ! PongMessage
        sender ! message
    case StopMessage =>
        println("pong stopped")
        context.stop(self)
    case msg: ACLMessage =>
        println(msg.parameters.get(ACLMessageParameter.PERFORMATIVE).orNull + ":" + msg.parameters.get(ACLMessageParameter.CONTENT).orNull)
        println(msg.parameters.get(ACLMessageParameter.SENDER).orNull.asInstanceOf[ActorRef])
  }
}

object Teste extends App{
  val system = ActorSystem("PingPongSystem")
  val pong = system.actorOf(Props[Pong], name = "pong")
  val ping = system.actorOf(Props(new Ping(pong)), name = "ping")
  // start them going
  ping ! StartMessage
}