package br.unb.cic.sma.sade.fipa

object ACLMessageParameter extends Enumeration{
  type ACLMessageParameter = Value
  val PERFORMATIVE = Value("performative")
  val SENDER = Value("sender")
  val RECEIVER = Value("receiver")
  val REPLY_TO = Value("reply-to")
  val CONTENT = Value("content")
  val LANGUAGE = Value("language")
  val ENCODING = Value("encoding")
  val ONTOLOGY = Value("ontology")
  val PROTOCOL = Value("protocol")
  val CONVERSATION_ID = Value("conversation-id")
  val REPLY_WITH = Value("reply-with")
  val IN_REPLY_TO = Value("in-reply-to")
  val REPLY_BY = Value("reply-by")
}