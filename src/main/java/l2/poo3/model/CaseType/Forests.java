package l2.poo3.model.CaseType;

import l2.poo3.model.CaseModel;
import l2.poo3.model.Enum.Case;
import l2.poo3.model.Enum.Resources;

public class Forests extends CaseModel {

    private final Resources produce = Resources.BOIS;

    public Forests() {
        super(Case.FORET, Resources.BOIS);
    }

}
