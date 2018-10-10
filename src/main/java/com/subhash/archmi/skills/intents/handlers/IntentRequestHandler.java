package com.subhash.archmi.skills.intents.handlers;

import java.util.ArrayList;
import java.util.List;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.Directive;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.dialog.directives.DelegateDirective;
import com.amazon.speech.speechlet.dialog.directives.DialogIntent;

public abstract class IntentRequestHandler {

    public abstract SpeechletResponse handle(SpeechletRequestEnvelope<IntentRequest> request);
    
    public SpeechletResponse delegateBackToAlexa(Intent intent) {
		DialogIntent dialogIntent = new DialogIntent(intent);
		DelegateDirective dd = new DelegateDirective();
		dd.setUpdatedIntent(dialogIntent);
		List<Directive> directiveList = new ArrayList<Directive>();
		directiveList.add(dd);

		SpeechletResponse speechletResp = new SpeechletResponse();
		speechletResp.setDirectives(directiveList);
		speechletResp.setNullableShouldEndSession(false);
		return speechletResp;
	}

}
