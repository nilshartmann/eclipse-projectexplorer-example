/*******************************************************************************
 * Copyright (c) 2011 Bundlemaker project team.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Bundlemaker project team - initial API and implementation
 ******************************************************************************/
package nh.projectexplorer;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

/**
 * This enumeration provides a number of images for the plugin.
 * 
 * <p>
 * Taken from http://blogs.itemis.de/wendehal/2010/07/08/pretty-elegant-way-to-provide -images-in-eclipse-ui-plug-ins/
 */
public enum UIImages {

  FOLDER_ICON("icons/folder.gif"), //
  FILE_ICON("icons/file.gif"), //
  ALLARCHIVES_ICON("icons/allarchives.gif"), //
  ARCHIVE_ICON("icons/archive.gif");

  /**
   * The bundle-relative path to the icon
   */
  private final String path;

  private UIImages(final String path) {
    this.path = path;
  }

  /**
   * Returns an image. Clients do not need to dispose the image, it will be disposed automatically.
   * 
   * @return an {@link Image}
   */
  public Image getImage() {
    final ImageRegistry imageRegistry = Activator.getDefault().getImageRegistry();
    Image image = imageRegistry.get(this.path);
    if (image == null) {
      addImageDescriptor();
      image = imageRegistry.get(this.path);
    }

    return image;
  }

  /**
   * Returns an image descriptor.
   * 
   * @return an {@link ImageDescriptor}
   */
  public ImageDescriptor getImageDescriptor() {
    final ImageRegistry imageRegistry = Activator.getDefault().getImageRegistry();
    ImageDescriptor imageDescriptor = imageRegistry.getDescriptor(this.path);
    if (imageDescriptor == null) {
      addImageDescriptor();
      imageDescriptor = imageRegistry.getDescriptor(this.path);
    }

    return imageDescriptor;
  }

  private void addImageDescriptor() {
    final Activator plugin = Activator.getDefault();
    final ImageDescriptor id = ImageDescriptor.createFromURL(plugin.getBundle().getEntry(this.path));
    plugin.getImageRegistry().put(this.path, id);
  }

}
