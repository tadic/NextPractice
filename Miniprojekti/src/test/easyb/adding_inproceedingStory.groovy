//import UI.*
//import controllers.*
//import entity.*
//import repositories.*
//import exception.*
//import miniprojekti.*
//
//description """As a user I can add Inproceeding"""
//
//scenario "Inproceeding gets saved as bibtex when all mandatory fields are filled", {
//    given 'form to fill information for a inproceeding',{
//        logic = new Logic()
//        converter = new Converter()
//        stub = new StubGUI(logic, converter)
//        stub.initGUI()
//        stub.openReferenceForm()
//    }
//    when 'every mandatory field is filled', {
//        fields = stub.getTextFields()
//        fields.get(0).setText("test")
//        fields.get(1).setText("Tero")
//        fields.get(2).setText("Elämän sietämätön keveys")
//        fields.get(3).setText("Tiuhti ja Viuhti vei mörön aarteen")
//        fields.get(4).setText("2001")
//        fields.get(fields.size() - 1).setText("testFile");
//        stub.saveButton()
//    }
//    then 'inproceeding gets saved as bibtex',{
//        file = new File("testFile.txt")
//        file.length().shouldBeGreaterThan 5
//        file.delete()
//    }
//}
//
//scenario "Inproceeding does not get saved as bibtex when year field is left empty", {
//    given 'form to fill information for a inproceeding',{
//        logic = new Logic()
//        converter = new Converter()
//        stub = new StubGUI(logic, converter)
//        stub.initGUI()
//        stub.openReferenceForm()
//    }
//    when 'every mandatory field is filled', {
//        fields = stub.getTextFields()
//        fields.get(0).setText("test")
//        fields.get(1).setText("Tero")
//        fields.get(2).setText("Elämän sietämätön keveys")
//        fields.get(3).setText("Tiuhti ja Viuhti vei mörön aarteen")
//        fields.get(4).setText("")
//        fields.get(fields.size() - 1).setText("testFile");
//        stub.saveButton()
//    }
//    then 'inproceeding gets saved as bibtex',{
//        file = new File("testFile.txt")
//        file.length().shouldBeLessThan 5
//        file.delete()
//        
//    }
//}
//scenario "Inproceeding does not get saved as bibtex when authors name is just one character", {
//    given 'form to fill information for a inproceeding',{
//        logic = new Logic()
//        converter = new Converter()
//        stub = new StubGUI(logic, converter)
//        stub.initGUI()
//        stub.openReferenceForm()
//    }
//    when 'every mandatory field is filled', {
//        fields = stub.getTextFields()
//        fields.get(0).setText("test")
//        fields.get(1).setText("a")
//        fields.get(2).setText("Elämän sietämätön keveys")
//        fields.get(3).setText("Tiuhti ja Viuhti vei mörön aarteen")
//        fields.get(4).setText("1999")
//        fields.get(fields.size() - 1).setText("testFile");
//        stub.saveButton()
//    }
//    then 'inproceeding gets saved as bibtex',{
//        file = new File("testFile.txt")
//        file.length().shouldBeLessThan 5
//        file.delete()
//        
//    }
//}
