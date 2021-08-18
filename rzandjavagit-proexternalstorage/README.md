# rzandjavagit-proexternalstorage
Rz Android Java Git SDK

### GIT Command
```code_work
String dirName = "TestDirectory";
String fileName = "test-" + System.currentTimeMillis() + ".txt";
//String directoryPath = ExternalStorage.getWriteableDirectory(context, ExternalStorage.DirectoryType.DIRECTORY_DOCUMENTS, dirName, true);
String directoryPath = ExternalStorage.getWriteableDirectory(context, ExternalStorage.DirectoryType.NULL, dirName);
String fileData = " hi this is a text file. " + directoryPath;
TextFile.writeData(fileData, directoryPath, fileName);
String fullFilePath = directoryPath + "/" + fileName;
System.out.println("======================> " + TextFile.readData(fullFilePath));
//ExternalStorage.onForceDelete(directoryPath);
```