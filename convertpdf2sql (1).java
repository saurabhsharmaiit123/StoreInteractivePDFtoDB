import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import static chirpexammain.ChirpExamMain.filename;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDComboBox;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDVariableText;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSString;

public class DemoprojectApplication {

  private static final String DB_URL = "jdbc:derby:E:\\support\\data\\derby.db;create=true";

  // Declaration of variables to store field values extracted from the PDF
  public static String txtFldFName;
  public static String txtFldLName;
  public static String txtFldPhone;
  public static String txtFldEMail;
  public static String txtFldCOName;
  public static String txtFldCOTitle;
  public static String txtFldSupr;
  public static String txtFldSuprPhone;
  public static String txtFldProject;
  public static String Answer1;
  public static String Answer2;
  public static String Answer3;
  public static String Answer4;
  public static String Answer5;
  public static String Answer6;
  public static String Answer7;
  public static String Answer8;
  public static String Answer9;
  public static String Answer10;
  public static String Answer11;
  public static String Answer12;
  public static String Answer13;
  public static String Answer14;
  public static String Answer15;
  public static String Answer16;
  public static String Answer17;
  public static String Answer18;
  public static String Answer19;
  public static String Answer20;
  public static String Answer21;
  public static String Answer22;
  public static String Answer23;
  public static String Answer24;
  public static String Answer25;
  public static String Answer26;
  public static String Answer27;
  public static String Answer28;
  public static String Answer29;
  public static String Answer30;

  public static void main(String[] args) {
    ClassLoader classLoader = DemoprojectApplication.class.getClassLoader();
    // java.net.URL resource = classLoader.getResource(fileNAme);
    java.net.URL resource = classLoader.getResource("ChirpTemplate.pdf");
    if (resource != null) {
      File pdfFile = new File(resource.getFile());
      extractFormFields(pdfFile);
      saveToDatabase();
    } else {
      System.err.println("PDF file not found in resources");
    }
    try {
      Driver derbyDriver = DriverManager.getDriver("jdbc:derby:");
      System.out.println("Derby JDBC driver is registered.");
    } catch (SQLException e) {
      System.err.println("Derby JDBC driver is not registered.");
    }
  }

  // Extracts form fields from the given PDF file
  public static void extractFormFields(File pdfFile) {
    try (PDDocument document = PDDocument.load(pdfFile)) {
      PDPage page = document.getPage(0);
      PDResources resources = page.getResources();
      PDAcroForm acroForm = document.getDocumentCatalog().getAcroForm();
      if (acroForm != null) {
        List<PDField> fields = acroForm.getFields();
        for (PDField field : fields) {
          if (field instanceof PDComboBox) {
            PDComboBox comboBox = (PDComboBox) field;
            extractComboBoxValue(comboBox, resources);
          } else if (field instanceof PDVariableText) {
            extractTextFieldValue(field, resources);
          }
        }
      }
    } catch (IOException e) {
    }
  }

  // Extracts the value from a combo box field
  private static void extractComboBoxValue(PDComboBox comboBox, PDResources resources) {
    String fieldName = comboBox.getFullyQualifiedName();
    List<String> selectedValues = comboBox.getValue();
    if (!selectedValues.isEmpty()) {
      String selectedValue = selectedValues.get(0); // Retrieve the first selected value
      assignValue(fieldName, selectedValue);
    }
  }

  // Extracts the value from a text field
  private static void extractTextFieldValue(PDField field, PDResources resources) {
    String fieldName = field.getFullyQualifiedName();
    COSDictionary dictionary = field.getCOSObject();
    COSString fieldValue = (COSString) dictionary.getItem(COSName.V);
    String value = fieldValue.getString();
    if (value.length() > 50) {
      value.substring(0, 50);
    }
    assignValue(fieldName, value);
  }

  // Assigns the extracted value to the appropriate field variable
  private static void assignValue(String fieldName, String value) {
    switch (fieldName) {
      case "Text1":
        txtFldFName = value;
        break;
      case "Text2":
        txtFldLName = value;
        break;
      case "Text3":
        txtFldPhone = value;
        break;
      case "Text4":
        txtFldEMail = value;
        break;
      case "Text5":
        txtFldCOName = value;
        break;
      case "Text6":
        txtFldCOTitle = value;
        break;
      case "Text7":
        txtFldSupr = value;
        break;
      case "Text8":
        txtFldSuprPhone = value;
        break;
      case "Text9":
        txtFldProject = value;
        break;
      case "CB1":
        Answer1 = value;
        break;
      case "CB2":
        Answer2 = value;
        break;
      case "CB3":
        Answer3 = value;
        break;
      case "CB4_1":
        Answer4 = value;
        break;
      case "CB5":
        Answer5 = value;
        break;
      case "CB6":
        Answer6 = value;
        break;
      case "CB7":
        Answer7 = value;
        break;
      case "CB8":
        Answer8 = value;
        break;
      case "CB9":
        Answer9 = value;
        break;
      case "CB10":
        Answer10 = value;
        break;
      case "CB11":
        Answer11 = value;
        break;
      case "CB12":
        Answer12 = value;
        break;
      case "CB13":
        Answer13 = value;
        break;
      case "CB14":
        Answer14 = value;
        break;
      case "CB15":
        Answer15 = value;
        break;
      case "CB16_1":
        Answer16 = value;
        break;
      case "CB17":
        Answer17 = value;
        break;
      case "CB18":
        Answer18 = value;
        break;
      case "CB19":
        Answer19 = value;
        break;
      case "CB20":
        Answer20 = value;
        break;
      case "CB21":
        Answer21 = value;
        break;
      case "CB22":
        Answer22 = value;
        break;
      case "CB23":
        Answer23 = value;
        break;
      case "CB24":
        Answer24 = value;
        break;
      case "CB25":
        Answer25 = value;
        break;
      case "CB26":
        Answer26 = value;
        break;
      case "CB27":
        Answer27 = value;
        break;
      case "CB28":
        Answer28 = value;
        break;
      case "CB29":
        Answer29 = value;
        break;
      case "CB30":
        Answer30 = value;
        break;
    }
  }

  private static void saveToDatabase() {
    try (Connection connection = DriverManager.getConnection(DB_URL)) {
      String query = "CREATE TABLE t9(" +
          "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY," +
          "field_name VARCHAR(500)," +
          "field_value VARCHAR(500)" +
          ")";
      PreparedStatement statement1 = connection.prepareStatement(query);
      statement1.executeUpdate();
      String query1 = "INSERT INTO t9 (field_name, field_value) VALUES (?, ?)";
      PreparedStatement statement = connection.prepareStatement(query1);

      statement.setString(1, "txtFldFName");
      statement.setString(2, txtFldFName);
      statement.executeUpdate();

      statement.setString(1, "txtFldLName");
      statement.setString(2, txtFldLName);
      statement.executeUpdate();

      statement.setString(1, "txtFldPhone");
      statement.setString(2, txtFldPhone);
      statement.executeUpdate();

      statement.setString(1, "txtFldEMail");
      statement.setString(2, txtFldEMail);
      statement.executeUpdate();

      statement.setString(1, "txtFldCOName");
      statement.setString(2, txtFldCOName);
      statement.executeUpdate();

      statement.setString(1, "txtFldCOTitle");
      statement.setString(2, txtFldCOTitle);
      statement.executeUpdate();

      statement.setString(1, "txtFldSupr");
      statement.setString(2, txtFldSupr);
      statement.executeUpdate();

      statement.setString(1, "txtFldSuprPhone");
      statement.setString(2, txtFldSuprPhone);
      statement.executeUpdate();

      statement.setString(1, "txtFldProject");
      statement.setString(2, txtFldProject);
      statement.executeUpdate();

      statement.setString(1, "Answer1");
      statement.setString(2, Answer1);
      statement.executeUpdate();

      statement.setString(1, "Answer2");
      statement.setString(2, Answer2);
      statement.executeUpdate();

      statement.setString(1, "Answer3");
      statement.setString(2, Answer3);
      statement.executeUpdate();

      statement.setString(1, "Answer4");
      statement.setString(2, Answer4);
      statement.executeUpdate();

      statement.setString(1, "Answer5");
      statement.setString(2, Answer5);
      statement.executeUpdate();

      statement.setString(1, "Answer6");
      statement.setString(2, Answer6);
      statement.executeUpdate();

      statement.setString(1, "Answer7");
      statement.setString(2, Answer7);
      statement.executeUpdate();

      statement.setString(1, "Answer8");
      statement.setString(2, Answer8);
      statement.executeUpdate();

      statement.setString(1, "Answer9");
      statement.setString(2, Answer9);
      statement.executeUpdate();

      statement.setString(1, "Answer10");
      statement.setString(2, Answer10);
      statement.executeUpdate();

      statement.setString(1, "Answer11");
      statement.setString(2, Answer11);
      statement.executeUpdate();

      statement.setString(1, "Answer12");
      statement.setString(2, Answer12);
      statement.executeUpdate();

      statement.setString(1, "Answer13");
      statement.setString(2, Answer13);
      statement.executeUpdate();

      statement.setString(1, "Answer14");
      statement.setString(2, Answer14);
      statement.executeUpdate();

      statement.setString(1, "Answer15");
      statement.setString(2, Answer15);
      statement.executeUpdate();

      statement.setString(1, "Answer16");
      statement.setString(2, Answer16);
      statement.executeUpdate();

      statement.setString(1, "Answer17");
      statement.setString(2, Answer17);
      statement.executeUpdate();

      statement.setString(1, "Answer18");
      statement.setString(2, Answer18);
      statement.executeUpdate();

      statement.setString(1, "Answer19");
      statement.setString(2, Answer19);
      statement.executeUpdate();

      statement.setString(1, "Answer20");
      statement.setString(2, "Answer20");
      statement.executeUpdate();

      statement.setString(1, "Answer21");
      statement.setString(2, Answer21);
      statement.executeUpdate();

      statement.setString(1, "Answer22");
      statement.setString(2, Answer22);
      statement.executeUpdate();

      statement.setString(1, "Answer23");
      statement.setString(2, Answer23);
      statement.executeUpdate();

      statement.setString(1, "Answer24");
      statement.setString(2, Answer24);
      statement.executeUpdate();

      statement.setString(1, "Answer25");
      statement.setString(2, Answer25);
      statement.executeUpdate();

      statement.setString(1, "Answer26");
      statement.setString(2, Answer26);
      statement.executeUpdate();

      statement.setString(1, "Answer27");
      statement.setString(2, Answer27);
      statement.executeUpdate();

      statement.setString(1, "Answer28");
      statement.setString(2, Answer28);
      statement.executeUpdate();

      statement.setString(1, "Answer29");
      statement.setString(2, Answer29);
      statement.executeUpdate();

      statement.setString(1, "Answer30");
      statement.setString(2, Answer30);
      statement.executeUpdate();

      System.out.println("Data stored in the database successfully.");

    } catch (SQLException e) {
    }

  }
}
