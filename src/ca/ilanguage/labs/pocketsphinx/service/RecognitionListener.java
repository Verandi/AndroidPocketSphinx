package ca.ilanguage.labs.pocketsphinx.service;

import android.os.Bundle;

/**
 * Interface for speech recognition callbacks.
 * 
 * Essentially a cut-down version of {@link android.speech.RecognitionListener},
 * to avoid dependencies on Froyo and methods we don't need or can't provide.
 * 
 * Original list;
 * abstract void onPartialResults(Bundle b);

	abstract void onResults(Bundle b);

	abstract void onError(int err);
	
	
	Based on commit 94062517ae5573f9a1a8
	https://github.com/android/platform_frameworks_base/blob/master/core/java/android/speech/RecognitionListener.java
 * 
 * @author David Huggins-Daines <dhuggins@cs.cmu.edu>
 * 
 */


import android.content.Intent;
import android.os.Bundle;

/**
 * Used for receiving notifications from the SpeechRecognizer when the
 * recognition related events occur. All the callbacks are executed on the
 * Application main thread.
 */
public interface RecognitionListener {
    /**
     * Called when the endpointer is ready for the user to start speaking.
     * 
     * @param params parameters set by the recognition service. Reserved for future use.
     */
    void onReadyForSpeech(Bundle params);

    /**
     * The user has started to speak.
     */
    void onBeginningOfSpeech();

    /**
     * The sound level in the audio stream has changed. There is no guarantee that this method will
     * be called.
     * 
     * @param rmsdB the new RMS dB value
     */
    void onRmsChanged(float rmsdB);

    /**
     * More sound has been received. The purpose of this function is to allow giving feedback to the
     * user regarding the captured audio. There is no guarantee that this method will be called.
     * 
     * @param buffer a buffer containing a sequence of big-endian 16-bit integers representing a
     *        single channel audio stream. The sample rate is implementation dependent.
     */
    void onBufferReceived(byte[] buffer);

    /**
     * Called after the user stops speaking.
     */
    void onEndOfSpeech();

    /**
     * A network or recognition error occurred.
     * 
     * @param error code is defined in {@link SpeechRecognizer}
     */
    void onError(int error);

    /**
     * Called when recognition results are ready.
     * 
     * @param results the recognition results. To retrieve the results in {@code
     *        ArrayList&lt;String&gt;} format use {@link Bundle#getStringArrayList(String)} with
     *        {@link SpeechRecognizer#RESULTS_RECOGNITION} as a parameter
     */
    void onResults(Bundle results);

    /**
     * Called when partial recognition results are available. The callback might be called at any
     * time between {@link #onBeginningOfSpeech()} and {@link #onResults(Bundle)} when partial
     * results are ready. This method may be called zero, one or multiple times for each call to
     * {@link SpeechRecognizer#startListening(Intent)}, depending on the speech recognition
     * service implementation.  To request partial results, use
     * {@link RecognizerIntent#EXTRA_PARTIAL_RESULTS}
     * 
     * @param partialResults the returned results. To retrieve the results in
     *        ArrayList&lt;String&gt; format use {@link Bundle#getStringArrayList(String)} with
     *        {@link SpeechRecognizer#RESULTS_RECOGNITION} as a parameter
     */
    void onPartialResults(Bundle partialResults);

    /**
     * Reserved for adding future events.
     * 
     * @param eventType the type of the occurred event
     * @param params a Bundle containing the passed parameters
     */
    void onEvent(int eventType, Bundle params);
}