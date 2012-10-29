package com.waterwagen.mris;

public class TestingConstants
{
	public static final String EMAILADDRESS_FOR_JAKE = "peregrinet@gmail.com";
	public static final String ID_FOR_JAKE = "00000";
	public static final String FIRSTNAME_FOR_JAKE = "Jacob";
	public static final String LASTNAME_FOR_JAKE = "Taylor";
	public static final String PHONENUMBER_FOR_JAKE = "5401234567";
	
	public static final String CONTACTFILES_DIR = "./src/test/resources/contactfiles/";
	public static final String SIMPLE_INPUT_FILE_PATH = CONTACTFILES_DIR + "jacob_only_input_file.csv";
	public static final String SIMPLE_OUTPUT_FILE_PATH = "./mris_output_file.csv";
	public static final String SIMPLE_INPUT_FILE_CONTENTS = "\"00000\",\"Taylor\",\"Jacob\",\"5401234567\",\"00000\",\"Taylor\",\"Jacob\",\"5401234567\"";
	public static final String SIMPLE_OUTPUT_FILE_CONTENTS = "\"00000\",\"Taylor\",\"Jacob\",\"5401234567\",\"peregrinet@gmail.com\",\"00000\",\"Taylor\",\"Jacob\",\"5401234567\",\"peregrinet@gmail.com\"";
}