package br.unb.cic.sma.sade.fipa

case class RequestWhenever(parameters: Map[ACLMessageParameter.ACLMessageParameter, Any]) extends ACLMessage(parameters: Map[ACLMessageParameter.ACLMessageParameter, Any]) {
  def getParameters = (parameters - ACLMessageParameter.PERFORMATIVE) + (ACLMessageParameter.PERFORMATIVE -> Performative.REQUEST_WHENEVER)
  override def performative = Performative.REQUEST_WHENEVER  
}