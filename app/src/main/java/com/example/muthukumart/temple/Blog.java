package com.example.muthukumart.temple;

public class Blog {
    private String Name;
    private String Phone;
    private String Known_Work;
    private String Educational;
    private String City;
    private String District;

    public Blog(String Name,String Phone,String Known_work,String Educational,String City,String District)
    {
        this.Name = Name;
        this.Phone = Phone;
        this.Known_Work = Known_work;
        this.Educational = Educational;
        this.City = City;
        this.District  = District;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getKnown_Work() {
        return Known_Work;
    }

   public void setKnown_Work(String known_Work) {
        Known_Work = known_Work;
    }

    public String getEducational() {
        return Educational;
    }

    public void setEducational(String educational) {
        Educational = educational;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    /*  public Blog(String Name,String Phone,String Known_work,String Educational,String City,String District)
        {
            this.Name = Name;
            this.Phone = Phone;
            this.Known_Work = Known_work;
            this.Educational = Educational;
            this.City = City;
            this.District  = District;
        }
        public String getName() {

            return Name;
        }

        public void setName(String Name) {

            this.Name = Name;
        }
        public String getKnown_Work() {

            return Known_Work;
        }

        public void setKnown_Work(String Known_Work) {

            this.Known_Work = Known_Work;
        }

        public String getPhone() {

            return Phone;
        }

        public void setPhone(String Phone) {

            this.Phone = Phone;
        }
        public String getEducational() {

            return Educational;
        }

        public void setEducational(String Educational) {

            this.Educational = Educational;
        }
        public String getCity() {

            return City;
        }

        public void setCity(String City) {

            this.City = City;
        }
        public String getDistrict() {

            return District;
        }

        public void setDistrict(String District) {

            this.District = District;
        } */
    public Blog()
    {

    }

}
