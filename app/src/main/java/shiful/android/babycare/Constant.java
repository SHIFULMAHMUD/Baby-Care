package shiful.android.babycare;

public class Constant {

    public static final String MAIN_URL = "http://my-project.cf/baby/android";

    public static final String SIGNUP_URL = MAIN_URL+"/register.php";
    public static final String LOGIN_URL = MAIN_URL+"/login.php";
    //url for user view
    public static final String USER_VIEW_URL = MAIN_URL+"/view_user.php?cell=";
    //url for add baby
    public static final String ADD_BABY_URL = MAIN_URL+"/add_baby.php";
    //url for view baby
    public static final String VIEW_BABY_URL = MAIN_URL+"/view_baby.php?cell=";
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

    //share preference
    //We will use this to store the user cell number into shared preference
    public static final String SHARED_PREF_NAME = "shiful.android.babycare"; //pcakage name+ id

    //This would be used to store the cell of current logged in user
    public static final String CELL_SHARED_PREF = "cell";


    //json array name.We will received data in this array
    public static final String JSON_ARRAY = "result";
}