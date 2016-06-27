package br.unb.cic.sma.sade.fipa

import scala.collection.immutable.HashMap

case class ACLMessage(parameters: HashMap[ACLMessageParameter.ACLMessageParameter, Any]){
  def frame = "fipa-acl-message"
  def ontology = "fipa-acl"
  
  def reply(performative: Performative.Performative, content: Any): ACLMessage = {
    val to = if(parameters.contains(ACLMessageParameter.REPLY_TO)){ 
              parameters.get(ACLMessageParameter.REPLY_TO) 
            } else { parameters.get(ACLMessageParameter.SENDER) }
    val sender = (ACLMessageParameter.SENDER -> parameters.get(ACLMessageParameter.RECEIVER))
    val replyTo = (ACLMessageParameter.REPLY_TO -> parameters.get(ACLMessageParameter.RECEIVER))
    val receiver = (ACLMessageParameter.RECEIVER -> to)                    
    val perform = (ACLMessageParameter.PERFORMATIVE, performative)
    val cont = (ACLMessageParameter.CONTENT, content)
    val par = parameters - ACLMessageParameter.PERFORMATIVE - 
                 ACLMessageParameter.CONTENT - ACLMessageParameter.SENDER - 
                 ACLMessageParameter.RECEIVER - ACLMessageParameter.REPLY_TO + 
                 perform + cont + sender + replyTo + receiver
    ACLMessage(par)
  }
}

case class ACLWrapper(message: ACLMessage)
