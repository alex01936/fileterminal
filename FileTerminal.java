package Tree;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class FileTerminal {

    private String workingDirectory = new File(".").getAbsolutePath();


    private void pwd() {

        System.out.println(workingDirectory);

    }

    private void cp(String sourcePath, String targetDirectory) throws IOException {

        File sourceLocation = new File(sourcePath);
        String[] splitted=sourcePath.split("/");
        String filename=splitted[splitted.length-1];

//        get file name from source path
        File newFile = new File(targetDirectory + File.separator + filename);

        InputStream in = new FileInputStream(sourceLocation);

        OutputStream out = new FileOutputStream(newFile);

        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
        System.out.println("cp command completed");

    }

    private void rm(String name) {
        File f = new File(name);
        if (f.isDirectory()) {
            File[] filelist = f.listFiles();
            for (File s : filelist) {
                if (s.isDirectory()) {
                    rm(s.getPath());
                }
                s.delete();
            }
            f.delete();
        }
        if (f.exists()) {
            f.delete();
        }
        System.out.println("rm command completed");
    }

    private void cd(String path) {
        File dir = new File(path);
        if (dir.exists() && dir.isDirectory()) {
            this.workingDirectory = path;
        }
        System.out.println("cd command completed");

    }

    private void ls() {
        for (File f : new File(workingDirectory).listFiles()) {
            if (f.isDirectory()) {
                System.out.println(f.getName() + " - [FOLDER]");
            } else System.out.println(f.getName() + " - [FILE]");
        }
        System.out.println("ls command completed");
    }

    private void cat(String path) {
        File f = new File(path);
        if (f.exists()) {
            try {
                FileReader fileReader = new FileReader(f);
                BufferedReader reader = new BufferedReader(fileReader);
                String text;
                while (true) {
                    try {
                        text = reader.readLine();
                        if (text == null) break;
                        System.out.println(text);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                System.out.println("cat command completed");

            } catch (FileNotFoundException e) {
                System.out.println("Wrong input!");
            }

        }

    }

    public boolean processCommand(TerminalCommand command, String[] input) throws IOException {
        System.out.println(command);

        switch (command) {
            case EXIT:
                return false;
            case PWD:
                pwd();
                break;
            case LS:
                ls();
                break;
            case CP: {
                cp(input[1],input[2]);
                break;
            }
            case CAT: {
                cat(input[1]);
                break;
            }
            case RM: {
                rm(input[1]);
                break;
            }
            case CD: {
                cd(input[1]);
                break;
            }

        }
        return true;
    }
}
