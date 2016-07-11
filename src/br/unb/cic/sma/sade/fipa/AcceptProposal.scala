package br.unb.cic.sma.sade.fipa

case class AcceptProposal(parameters: Map[ACLMessageParameter.ACLMessageParameter, Any]) extends ACLMessage(parameters: Map[ACLMessageParameter.ACLMessageParameter, Any]) {
  def getParameters = (parameters - ACLMessageParameter.PERFORMATIVE) + (ACLMessageParameter.PERFORMATIVE -> Performative.ACCEPT_PROPOSAL)
  override def performative = Performative.ACCEPT_PROPOSAL  
}