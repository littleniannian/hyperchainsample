package org.example.hyperchainservice.helper;

import java.io.*;

public class ContractFileHelper {
    //TODO:启动项目前更改文件位置
    private static final String FILE_PATH = "D:\\DEV\\ideaprojects\\hyperchain-mdtest\\hyperchain-service\\src\\main\\resources\\contract.txt";

    public static String readContent(){
        byte[] bytes = new byte[0];
        try {
            InputStream is = new FileInputStream(FILE_PATH);
            int iAvail = is.available();
            bytes = new byte[iAvail];
            is.read(bytes);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(bytes);
    }

    public static void writeContent(String content)throws IOException{
        File file = new File(FILE_PATH);
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
        bufferWriter.write(content);
        bufferWriter.close();
    }
}
