package com.subhash.archmi.skills.intents.handlers;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.IntentRequest.DialogState;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;

public class ArchFactsIntentHandler extends IntentRequestHandler {

	@Override
	public SpeechletResponse handle(SpeechletRequestEnvelope<IntentRequest> requestEnv) {

		IntentRequest request = requestEnv.getRequest();
		DialogState dialogueState = request.getDialogState();

		System.out.println("ArchFactsIntentHandler - DialogState - " + dialogueState);

		PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
		outputSpeech.setText("Arch MI is the industry leader");
		return SpeechletResponse.newTellResponse(outputSpeech);

	}

}
