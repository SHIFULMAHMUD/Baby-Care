package shiful.android.babycare;

public class Vac {
    private String mVaccineName;
    // Store the name of the movie
    private String mDoseName;
    // Store the release date of the movie
    private String mStartDate;
    private String mEndDate;
    private String mStatus;

    // Constructor that is used to create an instance of the Movie object

    public Vac(String mVaccineName, String mDoseName, String mStartDate, String mEndDate, String mStatus) {
        this.mVaccineName = mVaccineName;
        this.mDoseName = mDoseName;
        this.mStartDate = mStartDate;
        this.mEndDate = mEndDate;
        this.mStatus = mStatus;
    }

    public String getmVaccineName() {
        return mVaccineName;
    }

    public void setmVaccineName(String mVaccineName) {
        this.mVaccineName = mVaccineName;
    }

    public String getmDoseName() {
        return mDoseName;
    }

    public void setmDoseName(String mDoseName) {
        this.mDoseName = mDoseName;
    }

    public String getmStartDate() {
        return mStartDate;
    }

    public void setmStartDate(String mStartDate) {
        this.mStartDate = mStartDate;
    }

    public String getmEndDate() {
        return mEndDate;
    }

    public void setmEndDate(String mEndDate) {
        this.mEndDate = mEndDate;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }
}

