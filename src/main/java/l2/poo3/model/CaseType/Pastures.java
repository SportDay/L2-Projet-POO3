package l2.poo3.model.CaseType;

import l2.poo3.model.CaseModel;
import l2.poo3.model.Enum.Case;
import l2.poo3.model.Enum.Resources;

public class Pastures extends CaseModel {

    private final Resources produce = Resources.MOUTON;

    public Pastures() {
        super(Case.PRE, Resources.MOUTON);
    }
}
