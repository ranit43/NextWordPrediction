/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nextwordprediction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ranit
 */
public class NextWordPrediction {

    /**
     * @param args the command line arguments
     */
    
    public static ArrayList<String> al_comments = new ArrayList<String>();
    public static String documentsPath = "/media/ranit/soft & document/CodeOther/Java/AIDataSet/commentSeparation/positive_test_773.txt";
//    public static String documentsPath = "/home/ranit/Desktop/Test_corpus.txt";
    public static List< List< PairStringInt > > list_of_words = new ArrayList< List< PairStringInt > >();
    public static List< HashMap< String, Integer > > list_of_words_mp = new ArrayList< HashMap< String, Integer > >();
    public static HashMap< String, Integer > mp1_word_id = new HashMap<>();
    
    public static void main(String[] args) {
        // TODO code application logic here              
        read_comments();
        System.out.println("mp1_word_id  " + mp1_word_id.size() );
        int cnt = 0;
        
        int sz = mp1_word_id.size();
        for (Map.Entry<String, Integer> entry : mp1_word_id.entrySet())
        {
            System.out.println(entry.getKey() + "    "+entry.getValue() +"                       ----> --->        " );
            int idx = entry.getValue();
            HashMap< String, Integer > tt_cur_mp = list_of_words_mp.get(idx);
//            int cnt_2  = 0;
            for (Map.Entry<String, Integer> word_mp : tt_cur_mp.entrySet()) {
                System.out.println(word_mp.getKey() + " "+word_mp.getValue() +" $" );                
//                cnt_2++;   //                if(cnt_2>15) break;                
            }            
//            cnt++;
        }
    }
    
    
    private static void read_comments() {
        try {
            // Open the file that is the first 
            // command line parameter
            FileInputStream fstream = new FileInputStream(documentsPath);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String strLine;
            
            int fileCounter=1;            
            //Read File Line By Line
            while ((strLine = br.readLine()) != null)   {
            // Print the content on the console
            strLine = strLine + "\n";
//            System.out.println (strLine);
            al_comments.add(strLine);
            
            String tokens[] = strLine.split("[\u002c\u0964\u003f\u0021\\u0022\u0020\u002D\u003A"
                        + "\u09e6\u09e7\u09e8\u09e9\u09ea\u09eb\u09ec\u09ed\u09ee\u09ef]+");
            
            int len_tok = tokens.length;
//            for ( String ss : tokens) {
                for(int tk_idx = 0; tk_idx<len_tok; tk_idx++ ) {
                    String ss = tokens[ tk_idx ];

                    if(tk_idx+1<len_tok) {

                        if( mp1_word_id.get( ss ) == null ) {
                            mp1_word_id.put( ss,  mp1_word_id.size());
                        }
                        int id =   mp1_word_id.get(ss);
                        
                        if(list_of_words_mp.size() <= id ) {
                            list_of_words_mp.add( new HashMap< String, Integer >() );
                        }
                        HashMap< String, Integer > tt_cur_mp = list_of_words_mp.get(id);

                        String ss_next = tokens[ tk_idx+1 ];

                        if(tt_cur_mp.get( ss_next ) == null ) {
                            tt_cur_mp.put( ss_next, 1 );
                        }
                        else {
                            int cur_freq = tt_cur_mp.get( ss_next);
                            tt_cur_mp.put(ss_next, cur_freq+1);
                        }                                        
                    }
    //                System.out.print(ss+" + ");
                }            
            }
            //Close the input stream
            in.close();
            
        } catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        
    }
    
}
