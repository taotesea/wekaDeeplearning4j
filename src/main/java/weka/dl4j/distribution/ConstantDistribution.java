
package weka.dl4j.distribution;

import java.util.Enumeration;
import lombok.EqualsAndHashCode;
import org.nd4j.shade.jackson.annotation.JsonTypeName;
import weka.core.Option;
import weka.core.OptionHandler;
import weka.core.OptionMetadata;

/**
 * A version of DeepLearning4j's ConstantDistribution that implements WEKA option handling.
 *
 * @author Eibe Frank
 * @author Steven Lang
 */
@JsonTypeName("constant")
@EqualsAndHashCode(callSuper = true)
public class ConstantDistribution extends
    Distribution<org.deeplearning4j.nn.conf.distribution.ConstantDistribution> implements
    OptionHandler {


  private static final long serialVersionUID = -822639410912329551L;

  @OptionMetadata(
      displayName = "value",
      description = "Constant distribution value (default = 1.0).",
      commandLineParamName = "value",
      commandLineParamSynopsis = "-value <double>",
      displayOrder = 1
  )
  public double getValue() {
    return backend.getValue();
  }

  public void setValue(double value) {
    backend.setValue(value);
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

  @Override
  public void initializeBackend() {
    backend = new org.deeplearning4j.nn.conf.distribution.ConstantDistribution(1.0);
  }
}
