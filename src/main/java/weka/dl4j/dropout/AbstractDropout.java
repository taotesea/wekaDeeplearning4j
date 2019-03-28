
package weka.dl4j.dropout;

import java.io.Serializable;
import java.util.Enumeration;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.deeplearning4j.nn.conf.dropout.IDropout;
import weka.classifiers.functions.dl4j.Utils;
import weka.core.Option;
import weka.core.OptionHandler;
import weka.dl4j.ApiWrapper;
import weka.dl4j.ApiWrapperUtil;

/**
 * Abstract dropout class.
 *
 * @param <T> Dropout implementation
 * @author Steven Lang
 */
@EqualsAndHashCode
@ToString
public abstract class AbstractDropout<T extends IDropout> implements ApiWrapper<T>, OptionHandler,
    Serializable {

  private static final long serialVersionUID = -6503641755209671848L;

  T backend;

  public AbstractDropout() {
    // Run with init with local class loader since dropout backend init needs access to the
    // Nd4j backend
    Utils.runWithLocalClassloader(this.getClass(), this::initializeBackend);
  }

  /**
   * Create an API wrapped layer from a given layer object.
   *
   * @param newBackend Backend object
   * @return API wrapped object
   */
  public static AbstractDropout<? extends IDropout> create(IDropout
      newBackend) {
    return ApiWrapperUtil
        .getImplementingWrapper(AbstractDropout.class, newBackend, "weka.dl4j.dropout");
  }

  @Override
  public T getBackend() {
    return backend;
  }

  @Override
  public void setBackend(T newBackend) {
    this.backend = newBackend;
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
