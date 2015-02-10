import java.io.*;
public class FindDrive
{
public static void main(String[] args)throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] letters = new String[]{ "A", "B", "C", "D", "E", "F", "G", "H", "I"};
    File[] drives = new File[letters.length];
    boolean[] isDrive = new boolean[letters.length];
    // init the file objects and the initial drive state
    for ( int i = 0; i < letters.length; ++i )
        {
        drives[i] = new File(letters[i]+":/");
        isDrive[i] = drives[i].canRead();
        }

     System.out.println("FindDrive: waiting for devices...");

     // loop indefinitely
     while(true)
        {
        // check each drive 
        for ( int i = 0; i < letters.length; ++i )
            {
            boolean pluggedIn = drives[i].canRead();

            // if the state has changed output a message
            if ( pluggedIn != isDrive[i] )
                {
                if ( pluggedIn )
                {
                    System.out.println("Drive "+letters[i]+" has been plugged in!");
                    while(true)
                    {
                    System.out.println("Enter\n1. to find total space of the drive.\n2. to find free and used space.\n3. to see the files and folders in the drive.");
                    int ch=Integer.parseInt(br.readLine());
                    if(ch==1)
                        totalspace(drives[i]);
                    else if(ch==2)
                        freeused(drives[i]);
                    else if(ch==3)
                        folder(drives[i]);
                    else
                        System.out.println("Invalid choice.\nPlease enter the correct option!");
                    System.out.println("Want to check something again?\nEnter 1 for YES or 2 for NO!");
                    int choice=Integer.parseInt(br.readLine());
                    if(choice==2)
                        break;
                    else if(choice!=1)
                    {
                        System.out.println("Invalid choice.\nPlease enter the correct option!");
                        break;
                    }
                    }
                }
                else
                    System.out.println("Drive "+letters[i]+" has been unplugged!");

                isDrive[i] = pluggedIn;
                }
            }

        // wait before looping
        try { Thread.sleep(100); }
        catch (InterruptedException e) { /* do nothing */ }

        }
    }
    static void totalspace(File a)
    {
        float tsp=(float)a.getTotalSpace();
        float tspgbyte=(tsp/(1024*1024*1024));
        System.out.println("Total space : "+tspgbyte+" GB");
    }
    static void freeused(File b)
    {
        float tsp=(float)b.getTotalSpace();
        float tspgbyte=(tsp/(1024*1024*1024));
        float fsp=(float)b.getFreeSpace();
        float fspgbyte=(fsp/(1024*1024*1024));
        float uspgbyte=tspgbyte-fspgbyte;
        System.out.println("Free space : "+fspgbyte+" GB");
        System.out.println("Used space : "+uspgbyte+" GB");
    }
    static void folder(File c)
    {
      File[] listOfFiles = c.listFiles();
        for (File listOfFile : listOfFiles) 
        {
            if (listOfFile.isFile()) {
                System.out.println("File : " + listOfFile.getName());
            }else if (listOfFile.isDirectory()) {
                System.out.println("Folder : " + listOfFile.getName());
            }
        }  
    }
}