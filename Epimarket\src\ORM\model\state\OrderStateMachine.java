package ORM.model.state;

import ORM.model.validation.ValidationException;

public class OrderStateMachine
{
	/**
	 * The current method forwards the potential ValidationException declared by the IValidable.validate() method
	 * 
	 * @param statedReference
	 * @throws ValidationException
	 */
	public static void changeStep(IStateChangeable statedReference) throws ValidationException
	{
		//can call the IValidable interface validate() method, because IValidable extends IStateChangeable
		//any IStateChangeable reference is also a IValidable instance.
		statedReference.validate();
		
		statedReference.setState(statedReference.getState().getNextStepState());
	}

}
