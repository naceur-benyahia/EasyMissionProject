package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Administrator
 *
 */
@Entity
@DiscriminatorValue(value="administrator")

public class Administrator extends User implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Administrator() {
		super();
	}
   
}
