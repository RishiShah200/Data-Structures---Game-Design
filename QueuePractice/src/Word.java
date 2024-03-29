public class Word implements Comparable<Word> {

    private String word;

    public Word(String word){
        this.word = word;
    }

    @Override
    public int compareTo(Word o) {
        return -1*(getWord().toLowerCase().compareTo(o.getWord().toLowerCase()));
    }

    public String getWord(){
        return word;
    }

    public String toString(){
        return word;
    }

}
