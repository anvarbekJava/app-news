package uz.pdp.appnewssite.main;

public class Pen {
    private boolean button;
    private double inq; // ruchkadagi bor bo'lgan siyoh miqdori
    private String inqColor;
    private double inqConsumption; // bitta harf uchun sarflanadigan siyoh miqdori

    //    Alt+(Fn)Insert =>Constructor yaratish uchun
    public Pen() {
    }

    public Pen(double inq, String inqColor, double inqConsumption) {
        this.inq = inq;
        this.inqColor = inqColor;
        this.inqConsumption = inqConsumption;
    }

    public Pen(boolean button, double inq, String inqColor, double inqConsumption) {
        this.button = button;
        this.inq = inq;
        this.inqColor = inqColor;
        this.inqConsumption = inqConsumption;
    }

    public void clickButton() {
        button = !button;

//        if (button){
//            button=false;
//        }else {
//            button=true;
//        }
    }

    public void write(String word) {
        char[] ch = word.toCharArray();
        for (int i = ch[0]; i < ch.length; i++) {
            for (char j = 'A'; j <= 'Z'; j++)//check if uppercase
            {
                if (ch[i] ==j) {
                    System.out.println("Uppercase");
                    inq=inq-2*inqConsumption;
                    if (ch[i] == i) {

                        System.out.println("Vowel");
                    } else System.out.println("Not a vowel");
                }
            }

            for (i = 'a'; i <= 'z'; i++)//check if lowercase
            {
                if (ch[i] == i) {
                    System.out.println("Lowercase");
                    inq=inq-inqConsumption;
                    if (ch[i] == i) {

                        System.out.println("Vowel");
                    } else {
                        System.out.println("Not a vowel");
                    }
                }
            }
        }
    }

    public void changeSterjen(double inq) {
        this.inq = inq;
    }

    public boolean isButton() {
        return button;
    }

    public void setButton(boolean button) {
        this.button = button;
    }

    public double getInq() {
        return inq;
    }

    public void setInq(double inq) {
        if (inq > 0) {
            this.inq = inq;
        }

    }

    public String getInqColor() {
        return inqColor;
    }

    public void setInqColor(String inqColor) {
        this.inqColor = inqColor;
    }

    public double getInqConsumption() {
        return inqConsumption;
    }

    public void setInqConsumption(double inqConsumption) {
        if (inqConsumption > 0) {
            this.inqConsumption = inqConsumption;
        }
    }
}
