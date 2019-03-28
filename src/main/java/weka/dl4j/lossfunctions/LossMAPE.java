
package weka.dl4j.lossfunctions;

import java.util.Enumeration;
import org.nd4j.shade.jackson.annotation.JsonTypeName;
import weka.core.Option;
import weka.core.OptionHandler;

/**
 * A version of DeepLearning4j's LossMAPE that implements WEKA option handling.
 *
 * @author Eibe Frank
 */
@JsonTypeName("MAPE")
public class LossMAPE extends LossFunction<org.nd4j.linalg.lossfunctions.impl.LossMAPE> implements
    OptionHandler {

  private static final long serialVersionUID = 2960939259960582030L;

  /**
   * Returns an enumeration describing the available options.
   *
   * @return an enumeration of all the available options.
   */
  @Override
  public Enumeration<Option> listOptions() {

    return Option.listOptionsForClass(this.getClass()).elements();
  }

  /**
   * Gets the current settings of the Classifier.
   *
   * @return an array of strings suitable for passing to setOptions
   */
  @Override
  public String[] getOptions() {

    return Option.getOptions(this, this.getClass());
  }

  /**
   * Parses a given list of options.
   *
   * @param options the list of options as an array of strings
   * @throws Exception if an option is not supported
   */
  public void setOptions(String[] options) throws Exception {

    Option.setOptions(options, this, this.getClass());
  }

  @Override
  public void initializeBackend() {
    backend = new org.nd4j.linalg.lossfunctions.impl.LossMAPE();
  }
}
