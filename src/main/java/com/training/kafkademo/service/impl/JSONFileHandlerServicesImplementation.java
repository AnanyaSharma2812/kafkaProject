package com.training.kafkademo.service.impl;

import com.training.kafkademo.documentandentity.Employee;
import com.training.kafkademo.service.MyFileHandlerServices;
import net.minidev.json.JSONObject;
import org.json.simple.JSONArray;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;



public class JSONFileHandlerServicesImplementation implements MyFileHandlerServices
{
    Employee[] employee=new Employee[100];
    int employeeCounter=0;
    JSONObject tempEmployee;
    int count=0;
    String fileName="/Users/ananyasharma/Downloads/employee.json";
    try (FileReader reader = new FileReader(fileName))

    {
        JSONArray jo = (JSONArray) new org.json.simple.parser.JSONParser().parse(reader);
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        for (Object emp : jo)
        {
            employee[count] = new Employee();
            tempEmployee = (JSONObject) emp;
            employee[count].setFirstName((String) tempEmployee.get("firstName"));
            employee[count].setLastName((String) tempEmployee.get("lastName"));
            employee[count].setDateOfBirth(dateFormat.parse(tempEmployee.get("dateOfBirth").toString()));
            employee[count].setExperience((long) tempEmployee.get("experience"));
            count++;
        }
    }
    catch(IOException|org.json.simple.parser.ParseException|java.text.ParseException exception)
    {
        System.out.println(exception.getMessage());
    }

    @Override
    public Employee read()
    {
        return employee[employeeCounter++];
    }

    @Override
    public void write(Employee e)
    {

    }
}
