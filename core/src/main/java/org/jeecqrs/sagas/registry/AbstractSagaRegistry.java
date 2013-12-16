/*
 * Copyright (c) 2013 Red Rainbow IT Solutions GmbH, Germany
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.jeecqrs.sagas.registry;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import javax.ejb.Lock;
import javax.ejb.LockType;
import org.jeecqrs.sagas.Saga;
import org.jeecqrs.sagas.SagaRegistry;

public abstract class AbstractSagaRegistry<E> implements SagaRegistry<E> {

    private final Logger log = Logger.getLogger(this.getClass().getName());

    private final Set<Class<? extends Saga<E>>> sagas = new HashSet<>();

    @Override
    @Lock(LockType.READ)
    public Set<Class<? extends Saga<E>>> allSagas() {
        return new HashSet<>(sagas);
    }

    /**
     * Must only be called from {@code LockType.WRITE} protected methods.
     */
    protected void register(Class<? extends Saga<E>> saga) {
        sagas.add(saga);
    }

}
