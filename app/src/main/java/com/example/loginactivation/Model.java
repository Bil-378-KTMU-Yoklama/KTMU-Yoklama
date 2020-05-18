package com.example.loginactivation;

public class Model {
    String[] faculty = {"None","Engineering", "Economics and Management", "Sciences", "Theology", "Veterinary Medicine", "Tourism and Hotel Management", "Communication", "Humanities", "Fine Arts", "Agriculture", "Agriculture", "School of Foreign Languages"};
    String[] department = {"None", "Computer", "Chemical", "Environmental", "Food", "Industrial Department",
            "Economics", "Finance and Banking", "International Relations", "Management",
            "Applied Mathematics and Informatics", "Biology", "Mathematics",
            "Religious Studies", "Theology",
            "Veterinary Sciences",
            "Restaurant Management", "Tourism and Hotel Management", "Travel Business and Tourism Guidance",
            "School of Vocational Education",
            "Journalism", "PR and Advertising", "Radio, TV and Cinema",
            "Eastern Languages", "History", "Pedagogy", "Philosophy", "Sociology", "Synchronized Translation", "Turkology", "Western Languages",
    "Graphic Design", "Music", "Painting", "Theater", "Crop Protection", "Horticulture",
            "Department of Foreign Languages", "Department of Language Instruction", "Coaching Education", "Physical Education", ""};
    String[] status = {"None", "true", "false"};



    public String[] getFaculty(){
        return this.faculty;
    }
    public String[] getDepartment(){
        return this.department;
    }
    public String[] getStatus(){
        return this.status;
    }
}
