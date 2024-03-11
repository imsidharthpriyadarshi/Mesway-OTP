# Mesway OTP - Mobile OTP Sender

Mesway OTP is a solution that enables the automated sending of OTP (One Time Password) messages from your mobile device. By utilizing Firebase Cloud Messaging (FCM) for communication between your server and mobile device, Mesway OTP receives data notifications containing the OTP and requested mobile number, then automatically sends the OTP message from your mobile device to the specified number.

## Flow Overview

- **Server Setup**: Configure your server to send data notifications using Firebase Cloud Messaging (FCM). The server sends data notifications containing the OTP and requested mobile number.

- **Mesway OTP Mobile App Setup**: Install and configure the Mesway OTP mobile application on your mobile device. This application listens for incoming data notifications from FCM.

- **Notification receive**: When your server will send a data notification to FCM, Mesway OTP on your mobile device will receive the notification.

- **Data Extraction**: Mesway OTP extracts the OTP and the requested mobile number from the received notification.

- **OTP Sending**: The extracted mobile number is then used to send an OTP message from your mobile device.



## How you can setup this:
Follow these steps:-

### 1. Clone the repository into your new android project:

    ```bash
    git clone https://github.com/imsidharthpriyadarshi/Mesway-OTP.git
    ```

### 2. Obtaining Device Token/Notification token

To send notifications to your mobile device, you need to include the Device token in the body of your requests. Follow these steps to obtain the device token:

1. After cloning this repository and launch the Mesway OTP application on your mobile device.

2. Open the logcat console either via Android Studio or through the command line, search for the term "notification_token" in the logcat output.

3. Note down this device token as it uniquely identifies your device and will be required for sending notifications.

It's important to note that the device token remains constant unless you clear the app's data or reinstall the application.

By following these steps, you'll obtain the necessary device token required for sending notifications to your mobile device using Mesway OTP.

### 3. This is how you can send notification containing mobile number and otp from server (i am using Fastapi , Python)

     '''

    def send_otp_thorugh_my_mobile(mobile_number:str,otp:str):
        device_token=settings.device_token # your device token or notification token
        server_token=settings.notification_server_key # this token you can get from your firebase account
    
        headers={
            'Content-Type': 'application/json',
            'Authorization': 'key=' + server_token,
        }
        body={
            'registration_ids':[device_token],
            'data':{
            'number': mobile_number,
            'msg': f'Your OTP for Mesway login/signup is {otp} .This OTP is valid for only 10 mins.\nThanks, Mesway'
        },
        'priority': 'high'
        }
        response=requests.post("https://fcm.googleapis.com/fcm/send",headers=headers,data=json.dumps(body))
        data_response= response.json()  
    return True
    '''

