package br.unb.cic.sma.sade.fipa

import scala.collection.immutable.Map
import akka.actor.ActorRef

class ACLMessage(parameters: Map[ACLMessageParameter.ACLMessageParameter, Any]){
  def frame = "fipa-acl-message"
  def ontology = "fipa-acl"
  def sender = parameters.get(ACLMessageParameter.SENDER).orNull.asInstanceOf[ActorRef]
  def receiver = parameters.get(ACLMessageParameter.RECEIVER).orNull.asInstanceOf[ActorRef]
  def reply_to = if(parameters.contains(ACLMessageParameter.REPLY_TO)){ 
              parameters.get(ACLMessageParameter.REPLY_TO).orNull.asInstanceOf[ActorRef]
            } else { parameters.get(ACLMessageParameter.SENDER).orNull.asInstanceOf[ActorRef] }
  def content = parameters.get(ACLMessageParameter.CONTENT).orNull
  def performative = parameters.get(ACLMessageParameter.PERFORMATIVE).orNull.asInstanceOf[Performative.Performative]
  def getParameters = parameters
  
  def reply(performative: Performative.Performative, contentTo: Any): ACLMessage = {
    val par = (parameters - ACLMessageParameter.PERFORMATIVE - 
                 ACLMessageParameter.CONTENT - ACLMessageParameter.SENDER - 
                 ACLMessageParameter.RECEIVER - ACLMessageParameter.REPLY_TO) + 
                 (ACLMessageParameter.SENDER -> parameters.get(ACLMessageParameter.RECEIVER).get) +
                 (ACLMessageParameter.REPLY_TO -> parameters.get(ACLMessageParameter.RECEIVER).get) +
                 (ACLMessageParameter.RECEIVER -> reply_to) +
                 (ACLMessageParameter.PERFORMATIVE -> performative) +
                 (ACLMessageParameter.CONTENT -> contentTo)
    new ACLMessage(par)
  }
}