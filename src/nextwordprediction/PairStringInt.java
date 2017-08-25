/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nextwordprediction;

/**
 *
 * @author ranit
 */
public class PairStringInt {
    String cur_word;
    int word_freq;
    PairStringInt(String cw, int wf){
        this.cur_word = cw;
        this.word_freq = wf;
    }
    
    PairStringInt() {
        this.cur_word = "";
        this.word_freq = 0;
    }
}
