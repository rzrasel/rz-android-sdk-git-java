# rzandjavagit-prohttprequest
Rz Android Java Git SDK

### GIT Command
```code_work
String fileName = "sndr22.wav";
String fullFilePath =  directoryPath + "/" + fileName;
/*RequestBuilder requestBuilder = new RequestBuilder()
        .setFile("file", fileName, fullFilePath);
.build(AppConstant.HTTP.Url.uploadFile, Method.POST);*/
RequestBuilder requestBuilder = new RequestBuilder()
        .setFile("speech", fileName, fullFilePath);
ProFileUpload proFileUpload = new ProFileUpload();
proFileUpload
        .requestBuilder(requestBuilder)
        .setResponseListener(new ProFileUpload.ResponseListener() {
            @Override
            public void onSuccess(Response argResponse) {
                System.out.println("========================|SUCCESS|");
                System.out.println(argResponse.getMessage());
                System.out.println(argResponse.getBody());
            }

            @Override
            public void onFailure(Response argResponse) {
                System.out.println("========================|ERROR|");
                System.out.println(argResponse.getMessage());
                System.out.println(argResponse.getBody());
            }
        })
        .build(AppConstant.HTTP.Url.speechDetect, Method.POST);
```