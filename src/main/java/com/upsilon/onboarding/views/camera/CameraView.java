package com.upsilon.onboarding.views.camera;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.shared.communication.PushMode;
import elemental.json.Json;
import elemental.json.JsonObject;

@Push(value = PushMode.MANUAL)
@Route("camera")
public class CameraView extends VerticalLayout {

    private Registration clickListenerRegistration;
    private Div videoContainer;

    public CameraView() {
        videoContainer = new Div();
        videoContainer.addClassName("video-container");

        Button takePictureButton = new Button("Take Picture");
        takePictureButton.addClickListener(e -> takePicture());

        add(videoContainer, takePictureButton);

        setWidth("100%");
    }

    private void takePicture() {
        if (clickListenerRegistration == null) {
            clickListenerRegistration = addClickListener(event -> {
                // Trigger the JavaScript function to open the camera and capture the picture
                JsonObject params = Json.createObject();
                params.put("container", videoContainer.getElement().toString());
                getElement().executeJs(
                        "const video = document.createElement('video');" +
                                "video.setAttribute('autoplay', 'true');" +
                                "video.setAttribute('playsinline', 'true');" +
                                "const stream = navigator.mediaDevices.getUserMedia({ video: true });" +
                                "stream.then((mediaStream) => {" +
                                "   video.srcObject = mediaStream;" +
                                "   video.onloadedmetadata = () => {" +
                                "       const canvas = document.createElement('canvas');" +
                                "       canvas.width = video.videoWidth;" +
                                "       canvas.height = video.videoHeight;" +
                                "       canvas.getContext('2d').drawImage(video, 0, 0, canvas.width, canvas.height);" +
                                "       mediaStream.getTracks().forEach(track => track.stop());" +
                                "       const dataUrl = canvas.toDataURL('image/png');" +
                                "       $0.showCapturedImage(dataUrl);" +
                                "   };" +
                                "});", params);
            });
        }
    }

    @ClientCallable
    private void showCapturedImage(String dataUrl) {
        Image image = new Image(dataUrl, "Captured Image");
        add(image);

        if (clickListenerRegistration != null) {
            clickListenerRegistration.remove();
            clickListenerRegistration = null;
        }
    }
}
