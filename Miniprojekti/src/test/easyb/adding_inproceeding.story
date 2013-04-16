import Miniprojekti.*
import Miniprojekti.UI.*
import Miniprojekti.controllers.*
import Miniprojekti.entity.*
import Miniprojekti.repositories.*
import Miniprojekti.exception.*
import Miniprojekti.miniprojekti.*

description """As a user I can add Inproceeding"""

scenario "Inproceeding as bibtex when all mandatory fields are filled", {
    given 'form to fill information for a inproceeding'
    when 'every mandatory field is filled'
    then 'user sees inproceeding as bibtex'
}