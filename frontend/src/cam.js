function takePicture(canvas) {
    const video = document.createElement('video');
    video.setAttribute('autoplay', 'true');
    video.setAttribute('playsinline', 'true');
    const stream = navigator.mediaDevices.getUserMedia({ video: true });

    stream.then((mediaStream) => {
        video.srcObject = mediaStream;
        video.onloadedmetadata = () => {
            canvas.width = video.videoWidth;
            canvas.height = video.videoHeight;
            canvas.getContext('2d').drawImage(video, 0, 0, canvas.width, canvas.height);

            mediaStream.getTracks().forEach(track => track.stop());

            // Send the captured image data to the server
            const dataUrl = canvas.toDataURL('image/png');
            cameraView.showCapturedImage(dataUrl);
        };
    });
}
