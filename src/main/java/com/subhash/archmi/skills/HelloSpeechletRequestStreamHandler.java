package com.subhash.archmi.skills;

import java.util.HashSet;
import java.util.Set;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

public final class HelloSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {
    private static final Set<String> supportedApplicationIds;

    static {
        supportedApplicationIds = new HashSet<String>();
        supportedApplicationIds.add("amzn1.ask.skill.4909ad47-1851-49a2-bf12-dc1d902e90b7"); 
    }

    public HelloSpeechletRequestStreamHandler() {
        super(new HelloSkillsSpeechlet(), supportedApplicationIds);
    }
}
