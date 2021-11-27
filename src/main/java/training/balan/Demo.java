package training.balan;

public class Demo {

    public static void main(String[] args) throws InterruptedException {

        Employee director = new Director();

        Employee engineer = new Engineer();
        Employee engineer1 = new Engineer();
        Employee manager = new Manager();
        Company tesla = new Company();

        tesla.addEmployee(director);
        tesla.addEmployee(engineer);
        tesla.addEmployee(manager);

//        System.out.println(tesla);

        tesla.deleteAllWorkers();

//        System.out.println(tesla);


        for (int i = 0; i < 10; i++) {
            var manager1 = new Manager();
            System.out.println(manager1);
            tesla.addEmployee(manager1);
 //           Thread.sleep(1000);
        }

        System.out.println(tesla);

        System.out.println(tesla.getEmployeeById(5));


    }

}
