package com.subhash.archmi.skills;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.SpeechletV2;
import com.amazon.speech.ui.OutputSpeech;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import com.subhash.archmi.skills.constants.Constants;
import com.subhash.archmi.skills.intents.handlers.ArchFactsIntentHandler;

public class HelloSkillsSpeechlet implements SpeechletV2 {

	private static final Logger log = LoggerFactory.getLogger(HelloSkillsSpeechlet.class);

	@Override
	public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> requestEnvelope) {
		log.info("onSessionStarted requestId={}, sessionId={}", requestEnvelope.getRequest().getRequestId(),
				requestEnvelope.getSession().getSessionId());
		
		System.out.println(" onSessionStarted method");
	}

	@Override
	public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> requestEnvelope) {
		log.info("onLaunch requestId={}, sessionId={}", requestEnvelope.getRequest().getRequestId(),
				requestEnvelope.getSession().getSessionId());
		System.out.println(" LAUNCHED - onLaunch method");

		String speechText = Constants.WELCOME_TEXT;
		SpeechletResponse response = getAskResponse(Constants.CARD_TITLE_HELLOWORLD_SKILL, speechText);
//		response.setNullableShouldEndSession(true);
		return response;
	}

	@Override
	public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
		
		System.out.println(" INTENT HANDLING - onIntent method");
		IntentRequest request = requestEnvelope.getRequest();
		log.info("onIntent requestId={}, sessionId={}", request.getRequestId(),
				requestEnvelope.getSession().getSessionId());

		Intent intent = request.getIntent();
		String intentName = (intent != null) ? intent.getName() : null;

		if("ArchFactsIntent".equals(intentName)) {
			System.out.println("INSIDE ARCH FACTS INTENT");
			return new ArchFactsIntentHandler().handle(requestEnvelope);
		}
		else if ("AMAZON.HelpIntent".equals(intentName)) {
			
			return getHelpResponse();

		} else if ("AMAZON.StopIntent".equals(intentName)) {
			
			PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
			outputSpeech.setText("Goodbye");
			return SpeechletResponse.newTellResponse(outputSpeech);
			
		} else if ("AMAZON.CancelIntent".equals(intentName)) {
			
			PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
			outputSpeech.setText("Goodbye");
			return SpeechletResponse.newTellResponse(outputSpeech);
			
		} else {
			
			return getAskResponse(Constants.CARD_TITLE_HELLOWORLD_SKILL, "This is unsupported.  Please try something else.");
			
		}
	}

	@Override
	public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> requestEnvelope) {
		log.info("onSessionEnded requestId={}, sessionId={}", requestEnvelope.getRequest().getRequestId(),
				requestEnvelope.getSession().getSessionId());

		System.out.println("SESSION ENDED");
	}

	/**
	 * Returns a response for the help intent.
	 */
	private SpeechletResponse getHelpResponse() {
		String speechText = Constants.HELP_TEXT;
		return getAskResponse(Constants.CARD_TITLE_HELLOWORLD_SKILL, speechText);
	}


	private SimpleCard getSimpleCard(String title, String content) {
		SimpleCard card = new SimpleCard();
		card.setTitle(title);
		card.setContent(content);

		return card;
	}


	private PlainTextOutputSpeech getPlainTextOutputSpeech(String speechText) {
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);
		return speech;
	}


	private Reprompt getReprompt(OutputSpeech outputSpeech) {
		Reprompt reprompt = new Reprompt();
		reprompt.setOutputSpeech(outputSpeech);

		return reprompt;
	}

	private SpeechletResponse getAskResponse(String cardTitle, String speechText) {
		SimpleCard card = getSimpleCard(cardTitle, speechText);
		PlainTextOutputSpeech speech = getPlainTextOutputSpeech(speechText);
		Reprompt reprompt = getReprompt(speech);
		return SpeechletResponse.newAskResponse(speech, reprompt, card);
	}

}
