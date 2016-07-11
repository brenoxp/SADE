package br.unb.cic.sma.sade.fipa

case class RejectProposal(parameters: Map[ACLMessageParameter.ACLMessageParameter, Any]) extends ACLMessage(parameters: Map[ACLMessageParameter.ACLMessageParameter, Any]) {
  override def getParameters = (parameters - ACLMessageParameter.PERFORMATIVE) + (ACLMessageParameter.PERFORMATIVE -> Performative.REJECT_PROPOSAL)
  override def performative = Performative.REJECT_PROPOSAL  
}