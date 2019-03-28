
package weka.dl4j.activations;

import java.util.Enumeration;
import org.nd4j.shade.jackson.annotation.JsonTypeName;
import weka.core.Option;
import weka.core.OptionHandler;

/**
 * A version of DeepLearning4j's ActivationCube that implements WEKA option handling.
 *
 * @author Eibe Frank
 */
@JsonTypeName("Cube")
public class ActivationCube extends Activation<org.nd4j.linalg.activations.impl.ActivationCube>
    implements OptionHandler {

  private static final long serialVersionUID = 8926934844799196541L;

  @Override
  public void initializeBackend() {
    backend = new org.nd4j.linalg.activations.impl.ActivationCube();
  }

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
}
