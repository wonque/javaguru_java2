package lv.java2.shopping_list.web.dto.validation;

import javax.validation.GroupSequence;

@GroupSequence({NewEntry.PrimaryCheck.class, NewEntry.SecondaryCheck.class})
public interface NewEntry {

    interface PrimaryCheck{}

    interface SecondaryCheck{}
}
