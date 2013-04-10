import Miniprojekti.*

description """As a user I can add Inproceeding"""

scenario "Inproceeding as bibtex when all mandatory fields are filled", {
    given 'form to fill information for a inproceeding', {
        ui = new GUI()
        ui.initGUI()
    }
    when 'every mandatory field is filled', {
        // make methods to GUI! like getTextField, setText etc...
    }
    then 'user sees inproceeding as bibtex', {
        // and I could check the bibtex here
    }
}