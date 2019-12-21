package shiful.android.babycare;

public class Constant {

    public static final String MAIN_URL = "http://my-project.cf/baby/android";

    public static final String SIGNUP_URL = MAIN_URL+"/register.php";
    public static final String LOGIN_URL = MAIN_URL+"/login.php";
    //url for user view
    public static final String USER_VIEW_URL = MAIN_URL+"/view_user.php?cell=";
    //url for add baby
    public static final String ADD_BABY_URL = MAIN_URL+"/add_baby.php";
    //url for view doctor
    public static final String VIEW_DOC_URL = MAIN_URL+"/view_doctor.php";
    //url for view health center
    public static final String VIEW_HEALTH_CENTER_URL = MAIN_URL+"/view_health_center.php";
    //url for vaccine view
    public static final String VIEW_VACCINE_URL = MAIN_URL+"/vaccine.php";
    //url for view baby
    public static final String VIEW_BABY_URL = MAIN_URL+"/view_baby.php?cell=";
    //url for checkbox
    public static final String ADD_CHECKBOX_URL = MAIN_URL+"/checkbox.php";
    public static final String VIEW_CHECKBOX_URL = MAIN_URL+"/view_checkbox.php?cell=";
    //Keys for server communications
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_CELL = "cell";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_PASSWORD = "password";

    public static final String KEY_BABY_NAME = "baby_name";
    public static final String KEY_BABY_GENDER = "baby_gender";
    public static final String KEY_BLOODGROUP = "blood_group";
    public static final String KEY_DOB = "date_of_birth";
    public static final String KEY_BP = "birth_place";
    public static final String KEY_USER_CELL = "get_cell";

    public static final String KEY_DOCTOR_NAME = "doctor_name";
    public static final String KEY_DOCTOR_CELL = "doctor_cell";
    public static final String KEY_DOCTOR_ADDRESS = "doctor_address";
    public static final String KEY_DOCTOR_DESCRIPTION = "doctor_description";

    public static final String KEY_HC_NAME = "hc_name";
    public static final String KEY_HC_PHONE = "hc_phone";
    public static final String KEY_HC_LOCATION = "hc_location";
    public static final String KEY_HC_WEBSITE = "hc_website";

    public static final String KEY_VACCINE_NAME = "vaccine_name";
    public static final String KEY_DISEASE_NAME = "disease";
    public static final String KEY_DOSE_NO = "dose_no";
    public static final String KEY_DOSE_INTERVAL = "dose_interval";
    public static final String KEY_START_TIME = "start_time";
    public static final String KEY_INJECT_ROUTE = "route";

    public static final String KEY_VACCINE_ID = "vac_id";
    public static final String KEY_MODE = "mode";
    public static final String KEY_BABY_ID = "baby_id";
    //share preference
    //We will use this to store the user cell number into shared preference
    public static final String SHARED_PREF_NAME = "shiful.android.babycare"; //pcakage name+ id

    //This would be used to store the cell of current logged in user
    public static final String CELL_SHARED_PREF = "cell";

    //json array name.We will received data in this array
    public static final String JSON_ARRAY = "result";
}