package com.rzandjavagit.core.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GetRandom {
    public static int getRandomInRange(int argMin, int argMax) {
        return (new Random()).nextInt((argMax - argMin) + 1) + argMin;
    }

    public UniqueRandomInRange getUniqueRandomInRange(int argMin, int argMax) {
        return new UniqueRandomInRange(argMin, argMax);
    }

    public class UniqueRandomInRange {
        private int minVal, maxVal;
        private List<Integer> randomStore;

        //private HashSet randomStore = new HashSet();
        public UniqueRandomInRange getInstance(int argMin, int argMax) {
            return new UniqueRandomInRange(argMin, argMax);
        }

        private UniqueRandomInRange() {
        }

        private UniqueRandomInRange(int argMin, int argMax) {
            minVal = argMin;
            maxVal = argMax;
            randomStore = new ArrayList<Integer>();
            randomStore.clear();
            /*randomStore = new HashSet();
            randomStore.clear();*/
        }

        public int getUniqueRandomInRange() {
            int randVal = GetRandom.getRandomInRange(minVal, maxVal);
            /*
            //ConcurrentModificationException
            for (Integer item : randomStore) {
                if (item == randVal) {
                    getUniqueRandomInRange();
                }
            }*/
            int distance = (maxVal - minVal);
            if (randomStore.size() > distance) {
                randomStore.clear();
                System.out.println("Clear Data");
            }
            boolean isExists = true;
            while (isExists) {
                if (randomStore.contains(new Integer(randVal))) {
                    isExists = true;
                    randVal = GetRandom.getRandomInRange(minVal, maxVal);
                } else {
                    isExists = false;
                }
            }
            randomStore.add(new Integer(randVal));
            return randVal;
            /*for (Iterator<Integer> iterator = randomStore.iterator(); iterator.hasNext();) {
                Integer item = iterator.next();
                //if(item == randVal) {
                if(randomStore.contains(item)) {
                    getUniqueRandomInRange();
                }
            }*/
        }
    }
}
