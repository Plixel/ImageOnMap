/*

 * Copyright or © or Copr. Moribus (2013)
 * Copyright or © or Copr. ProkopyL <prokopylmc@gmail.com> (2015)
 * Copyright or © or Copr. Amaury Carrade <amaury@carrade.eu> (2016 – 2020)
 * Copyright or © or Copr. Vlammar <valentin.jabre@gmail.com> (2019 – 2020)
 *
 * This software is a computer program whose purpose is to allow insertion of
 * custom images in a Minecraft world.
 *
 * This software is governed by the CeCILL-B license under French law and
 * abiding by the rules of distribution of free software.  You can  use,
 * modify and/ or redistribute the software under the terms of the CeCILL-B
 * license as circulated by CEA, CNRS and INRIA at the following URL
 * "http://www.cecill.info".
 *
 * As a counterpart to the access to the source code and  rights to copy,
 * modify and redistribute granted by the license, users are provided only
 * with a limited warranty  and the software's author,  the holder of the
 * economic rights,  and the successive licensors  have only  limited
 * liability.
 *
 * In this respect, the user's attention is drawn to the risks associated
 * with loading,  using,  modifying and/or developing or reproducing the
 * software by the user in light of its specific status of free software,
 * that may mean  that it is complicated to manipulate,  and  that  also
 * therefore means  that it is reserved for developers  and  experienced
 * professionals having in-depth computer knowledge. Users are therefore
 * encouraged to load and test the software's suitability as regards their
 * requirements in conditions enabling the security of their systems and/or
 * data to be ensured and,  more generally, to use and operate it in the
 * same conditions as regards security.
 *
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL-B license and that you accept its terms.
 */

package fr.moribus.imageonmap;


import org.bukkit.permissions.Permissible;

public enum Permissions {
    NEW("imageonmap.new", "imageonmap.userender"),
    LIST("imageonmap.list"),
    LISTOTHER("imageonmap.listother"),
    GET("imageonmap.get"),
    GETOTHER("imageonmap.getother"),
    RENAME("imageonmap.rename"),
    REMOVE_SPLATTER_MAP("imageonmap.removesplattermap"),
    DELETE("imageonmap.delete"),
    DELETEOTHER("imageonmap.deleteother"),
    UPDATE("imageonmap.update"),
    UPDATEOTHER("imageonmap.updateother"),
    ADMINISTRATIVE("imageonmap.administrative"),
    BYPASS_SIZE("imageonmap.bypasssize"),
    GIVE("imageonmap.give");

    private final String permission;
    private final String[] aliases;

    Permissions(String permission, String... aliases) {
        this.permission = permission;
        this.aliases = aliases;
    }

    /**
     * Checks if this permission is granted to the given permissible.
     *
     * @param permissible The permissible to check.
     * @return {@code true} if this permission is granted to the permissible.
     */
    public boolean grantedTo(Permissible permissible) {
        if (permissible.hasPermission(permission)) {
            return true;
        }

        for (String alias : aliases) {
            if (permissible.hasPermission(alias)) {
                return true;
            }
        }

        return false;
    }
}