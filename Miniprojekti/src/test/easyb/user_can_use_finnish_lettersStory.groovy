//import UI.*
//import controllers.*
//import entity.*
//import repositories.*
//import exception.*
//import miniprojekti.*
//
//description """As a user I can use ääkköset"""
//
//scenario "User fills all mandatory forms where author is äiti and it gets saved right", {
//     given 'form to fill information for a inproceeding',{
//        logic = new Logic()
//        converter = new Converter()
//        stub = new StubGUI(logic, converter)
//        stub.initGUI()
//        stub.openReferenceForm()
//    }
//    when 'every mandatory field is filled', {
//        fields = stub.getTextFields()
//        fields.get(0).setText("test")
//        fields.get(1).setText("Äiti")
//        fields.get(2).setText("Elämän sietämätön keveys")
//        fields.get(3).setText("Tiuhti ja Viuhti vei mörön aarteen")
//        fields.get(4).setText("2001")
//        fields.get(fields.size() - 1).setText("testFile");
//        stub.saveButton()
//    }
//    then 'inproceeding gets saved as bibtex',{
//        file = new File("testFile.txt")
//        scanner = new Scanner(file)
//        text = ""
//        while (scanner.hasNext()) {
//            text += scanner.nextLine()
//        }
//        text.shouldHave("\"{A}iti")
//    }
//}