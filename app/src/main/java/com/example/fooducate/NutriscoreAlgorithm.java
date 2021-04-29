package com.example.fooducate;

public class NutriscoreAlgorithm {

    private static short getEnergyPositivePoints(final Float energy, final FoodType type) {
        final short result;

        if (type == FoodType.BEVERAGE) {
            if (energy == null || energy <= 0) {result = 0;}
            else if (energy <= 30) {result = 1;}
            else if (energy <= 60) {result = 2;}
            else if (energy <= 90) {result = 3;}
            else if (energy <= 120) {result = 4;}
            else if (energy <= 150) {result = 5;}
            else if (energy <= 180) {result = 6;}
            else if (energy <= 210) {result = 7;}
            else if (energy <= 240) {result = 8;}
            else if (energy <= 270) {result = 9;}
            else {result = 10;}
        } else {
            if (energy == null || energy <= 335) {result = 0;}
            else if (energy <= 670) {result = 1;}
            else if (energy <= 1005) {result = 2;}
            else if (energy <= 1340) {result = 3;}
            else if (energy <= 1675) {result = 4;}
            else if (energy <= 2010) {result = 5;}
            else if (energy <= 2345) {result = 6;}
            else if (energy <= 2680) {result = 7;}
            else if (energy <= 3015) {result = 8;}
            else if (energy <= 3350) {result = 9;}
            else {result = 10;}
        }

        return result;
    }

    private static short getSaturatedFatPoints(final Float fatMatter, final Float saturatedFat, final FoodType type) {

        final short result;

        if (saturatedFat == null) {
            result = 0;
        } else if (fatMatter == null) {
            throw new RuntimeException("fatMatter null and saturatedFat not null. Incongruent ratio");
        } else if (type == FoodType.FAT_MATTER) {
            float ratio = Math.round(saturatedFat / fatMatter * 10000) / 100;

            if (ratio < 10) {result = 0;}
            else if (ratio < 16) {result = 1;}
            else if (ratio < 22) {result = 2;}
            else if (ratio < 28) {result = 3;}
            else if (ratio < 34) {result = 4;}
            else if (ratio < 40) {result = 5;}
            else if (ratio < 46) {result = 6;}
            else if (ratio < 52) {result = 7;}
            else if (ratio < 58) {result = 8;}
            else if (ratio < 64) {result = 9;}
            else {result = 10;}

        } else {

            if (saturatedFat <= 1) {result = 0;}
            else if (saturatedFat <= 2) {result = 1;}
            else if (saturatedFat <= 3) {result = 2;}
            else if (saturatedFat <= 4) {result = 3;}
            else if (saturatedFat <= 5) {result = 4;}
            else if (saturatedFat <= 6) {result = 5;}
            else if (saturatedFat <= 7) {result = 6;}
            else if (saturatedFat <= 8) {result = 7;}
            else if (saturatedFat <= 9) {result = 8;}
            else if (saturatedFat <= 10) {result = 9;}
            else {result = 10;}

        }

        return result;
    }

    private static short getSugarPoints(final Float sugar, final FoodType type) {
        final short result;

        if (type == FoodType.BEVERAGE) {
            if (sugar == null || sugar <= 0) {result = 0;}
            else if (sugar <= 1.5) {result = 1;}
            else if (sugar <= 3) {result = 2;}
            else if (sugar <= 4.5) {result = 3;}
            else if (sugar <= 6) {result = 4;}
            else if (sugar <= 7.5) {result = 5;}
            else if (sugar <= 9) {result = 6;}
            else if (sugar <= 10.5) {result = 7;}
            else if (sugar <= 12) {result = 8;}
            else if (sugar <= 13.5) {result = 9;}
            else {result = 10;}

        } else {
            if (sugar == null || sugar <= 4.5) {result = 0;}
            else if (sugar <= 9) {result = 1;}
            else if (sugar <= 13.5) {result = 2;}
            else if (sugar <= 18) {result = 3;}
            else if (sugar <= 22.5) {result = 4;}
            else if (sugar <= 27) {result = 5;}
            else if (sugar <= 31) {result = 6;}
            else if (sugar <= 36) {result = 7;}
            else if (sugar <= 40) {result = 8;}
            else if (sugar <= 45) {result = 9;}
            else {result = 10;}
        }
        return result;
    }

    private static short getFruitPoints(final Float fruits, final FoodType type) {
        final short result;

        if (type == FoodType.BEVERAGE) {
            if (fruits == null || fruits <= 40) {result = 0;}
            else if (fruits <= 60) {result = 2;}
            else if (fruits <= 80) {result = 4;}
            else {result = 10;}
        } else {
            if (fruits == null || fruits <= 40) {result = 0;}
            else if (fruits <= 60) {result = 1;}
            else if (fruits <= 80) {result = 2;}
            else {result = 5;}
        }

        return result;
    }

    private static short getProteinsPoints(final Float proteins) {

        final short result;
        if (proteins == null || proteins <= 1.6) {result = 0;}
        else if (proteins <= 3.2) {result = 1;}
        else if (proteins <= 4.8) {result = 2;}
        else if (proteins <= 6.4) {result = 3;}
        else if (proteins <= 8) {result = 4;}
        else {result = 5;}

        return result;

    }

    private static short getFiberPoints(final Float fiber) {
        final short result;
        if (fiber == null || fiber <= 0.9) {result = 0;}
        else if (fiber <= 1.9) {result = 1;}
        else if (fiber <= 2.8) {result = 2;}
        else if (fiber <= 3.7) {result = 3;}
        else if (fiber <= 4.7) {result = 4;}
        else {result = 5;}
        return result;
    }

    private static short getSodiumPoints(Float sodium, final Float salt) {
        final short result;

        if(sodium==null && salt!=null) {
            sodium = salt / 2.5f;
        }

        if (sodium == null || sodium <= 90) {result = 0;}
        else if (sodium <= 180) {result=1;}
        else if (sodium <= 270) {result=2;}
        else if (sodium <= 360) {result = 3;}
        else if (sodium <= 450) {result = 4;}
        else if (sodium <= 540) {result = 5;}
        else if (sodium <= 630) {result = 6;}
        else if (sodium <= 720) {result = 7;}
        else if (sodium <= 810) {result = 8;}
        else if (sodium <= 900) {result = 9;}
        else {result = 10;}
        return result;
    }

    private static short getNutritionalScore(Product product, FoodType type) {

        final short result;

        final short fiberPoints = getFiberPoints(product.getNutriscoreData().getFiber());
        final short proteinsPoints = getProteinsPoints(product.getNutriscoreData().getProteins());
        final short fruitPoints = getFruitPoints(product.getNutriscoreData().getFruits(), type);
        final short energyPoints = getEnergyPositivePoints(product.getNutriscoreData().getEnergy(), type);
        final short saturatedFatPoints = getSaturatedFatPoints((float) product.getNutriments().getFat_100g(), product.getNutriscoreData().getSaturatedFat(), type);
        final short sugarPoints = getSugarPoints(product.getNutriscoreData().getSugars(), type);
        final short sodiumPoints = getSodiumPoints(product.getNutriscoreData().getSodium(), (float) product.getNutriments().getSalt_100g());

        final short positivePoints = (short) (fiberPoints + proteinsPoints + fruitPoints);
        final short negativePoints = (short) (energyPoints + saturatedFatPoints + sugarPoints + sodiumPoints);

        if (negativePoints >= 11 && type != FoodType.CHEESE && fruitPoints < 5) {
            result = (short) (negativePoints - fiberPoints - fruitPoints);
        } else {
            result = (short) (negativePoints - positivePoints);
        }

        return result;
    }

    public static String getNutriscore(Product product, FoodType type) {

        final String result;

        if (type == FoodType.WATER) {
            result = "A";
        } else {

            final short score = getNutritionalScore(product, type);

            if (type == FoodType.BEVERAGE) {
                if (score <= 1) {
                    result = "B";
                } else if (score <= 5) {
                    result = "C";
                } else if (score <= 9) {
                    result = "D";
                } else {
                    result = "E";
                }
            } else { // general, fatMatter and cheese
                if (score <= -1) {
                    result = "A";
                } else if (score <= 2) {
                    result = "B";
                } else if (score <= 10) {
                    result = "C";
                } else if (score <= 18) {
                    result = "D";
                } else {
                    result = "E";
                }
            }
        }
        return result;
    }

}
