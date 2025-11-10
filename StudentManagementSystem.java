import java.util.Scanner;

class StudentNode 
{
    int rollNo;
    String name;
    int marks;
    StudentNode next;
    
    public StudentNode(int rollNo, String name, int marks) 
    {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = marks;
        this.next = null;
    }
}

class StudentLinkedList 
{
    private StudentNode head;
    
    public StudentLinkedList() 
    {
        this.head = null;
    }
    
    public void addStudent(int rollNo, String name, int marks) 
    {
        StudentNode newNode = new StudentNode(rollNo, name, marks);
        if (head == null) 
        {
            head = newNode;
        } 
        else 
        {
            StudentNode current = head;
            while (current.next != null) 
            {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("Student record added.");
    }
    
    public void deleteStudent(int rollNo) 
    {
        StudentNode current = head;
        StudentNode prev = null;
        
        while (current != null) 
        {
            if (current.rollNo == rollNo) 
            {
                if (prev == null) 
                {
                    head = current.next;
                } 
                else 
                {
                    prev.next = current.next;
                }
                System.out.println("Student record deleted.");
                return;
            }
            prev = current;
            current = current.next;
        }
        System.out.println("Student not found.");
    }
    
    public void updateStudent(int rollNo, String newName, int newMarks) 
    {
        StudentNode current = head;
        while (current != null) 
        {
            if (current.rollNo == rollNo) 
            {
                current.name = newName;
                current.marks = newMarks;
                System.out.println("Student record updated.");
                return;
            }
            current = current.next;
        }
        System.out.println("Student not found.");
    }
    
    public void searchStudent(int rollNo) 
    {
        StudentNode current = head;
        while (current != null) 
        {
            if (current.rollNo == rollNo) 
            {
                System.out.println("🔍 Found: Roll No: " + current.rollNo + 
                                 ", Name: " + current.name + 
                                 ", Marks: " + current.marks);
                return;
            }
            current = current.next;
        }
        System.out.println("Student not found.");
    }
    
    public void displayStudents(String sortBy, boolean ascending) 
    {
        if (head == null) 
        {
            System.out.println("No records to display.");
            return;
        }
        
        // Convert linked list to array for sorting
        int count = 0;
        StudentNode current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        
        StudentNode[] students = new StudentNode[count];
        current = head;
        for (int i = 0; i < count; i++) 
        {
            students[i] = current;
            current = current.next;
        }
        
        // Sort the array
        if (sortBy.equals("roll_no")) 
        {
            bubbleSortByRollNo(students, ascending);
        } 
        else if (sortBy.equals("marks")) 
        {
            bubbleSortByMarks(students, ascending);
        }
        
        System.out.println("\nStudent Records:");
        for (StudentNode student : students) 
        {
            System.out.println("Roll No: " + student.rollNo + 
                             ", Name: " + student.name + 
                             ", Marks: " + student.marks);
        }
    }
    
    private void bubbleSortByRollNo(StudentNode[] arr, boolean ascending) 
    {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) 
            {
                boolean shouldSwap = ascending ? 
                    arr[j].rollNo > arr[j + 1].rollNo : 
                    arr[j].rollNo < arr[j + 1].rollNo;
                
                if (shouldSwap) 
                {
                    StudentNode temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    
    private void bubbleSortByMarks(StudentNode[] arr, boolean ascending) 
    {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) 
            {
                boolean shouldSwap = ascending ? 
                    arr[j].marks > arr[j + 1].marks : 
                    arr[j].marks < arr[j + 1].marks;
                
                if (shouldSwap) 
                {
                    StudentNode temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}

public class StudentManagementSystem 
{
    
    public static void menu() 
    {
        StudentLinkedList system = new StudentLinkedList();
        Scanner scanner = new Scanner(System.in);
        
        while (true) 
        {
            System.out.println("\n--- Student Record Management Menu ---");
            System.out.println("1. Add Student");
            System.out.println("2. Delete Student");
            System.out.println("3. Update Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            
            System.out.print("Enter your choice (1-6): ");
            String choice = scanner.nextLine();
            
            switch (choice) 
            {
                case "1":
                    System.out.print("Enter Roll No: ");
                    int roll = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Marks: ");
                    int marks = Integer.parseInt(scanner.nextLine());
                    system.addStudent(roll, name, marks);
                    break;
                    
                case "2":
                    System.out.print("Enter Roll No to Delete: ");
                    int delRoll = Integer.parseInt(scanner.nextLine());
                    system.deleteStudent(delRoll);
                    break;
                    
                case "3":
                    System.out.print("Enter Roll No to Update: ");
                    int updateRoll = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter New Name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter New Marks: ");
                    int newMarks = Integer.parseInt(scanner.nextLine());
                    system.updateStudent(updateRoll, newName, newMarks);
                    break;
                    
                case "4":
                    System.out.print("Enter Roll No to Search: ");
                    int searchRoll = Integer.parseInt(scanner.nextLine());
                    system.searchStudent(searchRoll);
                    break;
                    
                case "5":
                    System.out.print("Sort by 'roll_no' or 'marks': ");
                    String sortKey = scanner.nextLine();
                    System.out.print("Order 'asc' or 'desc': ");
                    String order = scanner.nextLine();
                    boolean ascending = order.equals("asc");
                    system.displayStudents(sortKey, ascending);
                    break;
                    
                case "6":
                    System.out.println("Exiting Student Record Management System.");
                    scanner.close();
                    return;
                    
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
    
    public static void main(String[] args) 
    {
        menu();
    }
}