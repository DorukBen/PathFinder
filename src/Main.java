import data.Map;
import data.Population;
import util.evolution.Evolution;
import org.jfree.ui.RefineryUtilities;
import util.FitnessUtils;
import util.SectionUtils;
import util.evolution.crossover.MultiPointCrossover;
import util.evolution.crossover.UniformCrossover;
import util.evolution.mutation.InversionMutation;
import util.evolution.mutation.RandomResettingMutation;
import util.evolution.mutation.ScrambleMutation;
import util.evolution.mutation.SwapMutation;
import util.evolution.selection.TournamentSelection;
import util.factory.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    private static SelectionFactory.SelectionType sSelection = SelectionFactory.SelectionType.FITNESS_PROPORTIONATE_SELECTION;
    private static CrossoverFactory.CrossoverType sCrossover = CrossoverFactory.CrossoverType.ONE_POINT_CROSSOVER;
    private static MutationFactory.MutationType sMutation = MutationFactory.MutationType.RANDOM_RESETTING_MUTATION;
    private static boolean sElitism = false;

    private static boolean sIsGUIActive = false;

    public static void main(String[] args) {
        if (printAbout()) {
            shellLoop();
        }
    }

    private static boolean printAbout() {
        System.out.println("Genetic Path Algorithm");
        System.out.println("Yildiz Technical University 2017\n");
        System.out.println("Declarable Variables:");
        System.out.println("[Map]:");
        System.out.println("    --[RowLength = 15]  Row length of the map");
        System.out.println("    --[ColumnLength = 15]  Column length of the map");
        System.out.println("    --[BlockChance = 0.3] Chance of blocked section occurrence\n");
        System.out.println("[Individual]:");
        System.out.println("    --[ChromosomeLength = 32] Gene count in a single chromosome\n");
        System.out.println("[Population]:");
        System.out.println("    --[PopulationSize = 64] Individual count in the population\n");
        System.out.println("[Selection]:");
        System.out.println("    --[SelectionType = FPS]: Built in selection functions");
        System.out.println("        --[FPS] Fitness Proportionate Selection");
        System.out.println("        --[TS] Tournament Selection");
        System.out.println("        --[RS] Rank Selection");
        System.out.println("        --[Rand] Random Selection\n");
        System.out.println("[Crossover]:");
        System.out.println("    --[CrossoverType = OPC]: Built in crossover functions");
        System.out.println("        --[OPC] One Point Crossover");
        System.out.println("        --[MPC] Multi Point Crossover");
        System.out.println("        --[UC] Uniform Crossover\n");
        System.out.println("[Mutation]:");
        System.out.println("    --[MutationType = RRM]: Built in mutation functions");
        System.out.println("        --[RRM] Random Resetting Mutation");
        System.out.println("        --[SWM] Swap Mutation");
        System.out.println("        --[SM] Scramble Mutation");
        System.out.println("        --[IM] Inversion Mutation\n");
        System.out.println("[Evolution]:");
        System.out.println("    --[Elitism = false]: Selection preference for using elitism\n");

        System.out.println("Start evolving?[Y/n]");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        clearShell();
        return Objects.equals(input, "y") || Objects.equals(input, "Y");
    }

    private static void shellLoop() {
        boolean status = true;
        while (status) {
            while (shellDeclaration()) ;
            shellRun();

            System.out.println("Again?[Y/n]");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            clearShell();
            status = Objects.equals(input, "y") || Objects.equals(input, "Y");
        }
    }

    private static void shellRun() {
        MapFactory mapFactory = new MapFactory();
        Map map = mapFactory.randomInstance();
        PopulationFactory populationFactory = new PopulationFactory();
        Population population = populationFactory.randomInstance();
        FitnessUtils.calculateFitness(map, population);

        final ChartView demo = new ChartView("GA",
                SectionUtils.getWritableData(map),
                SectionUtils.getWritablePathData(map, population),
                SectionUtils.getStartPoint(map),
                SectionUtils.getTargetPoint(map));
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
        sIsGUIActive = true;

        demo.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                sIsGUIActive = false;
            }
        });

        do{
            System.out.println("Generation: " + population.getGeneration() + " Fitness: "+ Evolution.mostAdaptive(population).getFitness());
            population = Evolution.evolve(population,
                    sSelection,
                    sCrossover,
                    sMutation,
                    sElitism);
            FitnessUtils.calculateFitness(map, population);

            demo.newData(
                    SectionUtils.getWritableData(map),
                    SectionUtils.getWritablePathData(map, population),
                    SectionUtils.getStartPoint(map),
                    SectionUtils.getTargetPoint(map)
            );
        } while(Evolution.mostAdaptive(population).getFitness() < SectionUtils.getMaximumDistance(map) && sIsGUIActive);
        if (sIsGUIActive) {
            System.out.println("Solution found!");
            System.out.println("Generation: "+ population.getGeneration());
            System.out.println("Genes:");
            System.out.println(Evolution.mostAdaptive(population));
        } else {
            System.out.println("Terminated!");
        }
    }

    private static boolean shellDeclaration() {
        System.out.println("Any Change?");
        System.out.println("1.[Map]\n2.[Individual]\n3.[Population]\n4.[Selection]\n5.[Crossover]\n6.[Mutation]\n7.[Evolution]");
        System.out.println("8.Start Now");
        Scanner scanner = new Scanner(System.in);
        int selection = scanner.nextInt();

        clearShell();

        switch (selection) {
            case 1:
                adjustMap();
                return true;
            case 2:
                adjustIndividual();
                return true;
            case 3:
                adjustPopulation();
                return true;
            case 4:
                adjustSelection();
                return true;
            case 5:
                adjustCrossover();
                return true;
            case 6:
                adjustMutation();
                return true;
            case 7:
                adjustEvolution();
                return true;
            case 8:
                return false;
            default:
                return true;
        }
    }

    private static void adjustMap() {
        System.out.println("Map:");
        Scanner scanner = new Scanner(System.in);

        System.out.print("RowLength: ");
        int rowLength = scanner.nextInt();
        MapFactory.setDefaultRowLength(rowLength);

        System.out.print("ColumnLength: ");
        int columnLength = scanner.nextInt();
        MapFactory.setDefaultColumnLength(columnLength);

        System.out.print("BlockChance: ");
        double blockChance = scanner.nextDouble();
        MapFactory.setDefaultBlockChance(blockChance);

        clearShell();
    }

    private static void adjustIndividual() {
        System.out.println("Individual:");
        Scanner scanner = new Scanner(System.in);

        System.out.print("ChromosomeLength: ");
        int chromosomeLength = scanner.nextInt();
        IndividualFactory.setChromosomeLength(chromosomeLength);

        clearShell();
    }

    private static void adjustPopulation() {
        System.out.println("Population:");
        Scanner scanner = new Scanner(System.in);

        System.out.print("PopulationSize: ");
        int populationSize = scanner.nextInt();
        PopulationFactory.setPopulationSize(populationSize);

        clearShell();
    }

    private static void adjustSelection() {
        System.out.println("Selection:");
        Scanner scanner = new Scanner(System.in);

        System.out.print("SelectionType: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "FPS":
                sSelection = SelectionFactory.SelectionType.FITNESS_PROPORTIONATE_SELECTION;
                break;

            case "TS":
                sSelection = SelectionFactory.SelectionType.TOURNAMENT_SELECTION;
                System.out.print("TournamentSize: ");
                int tournamentSize = scanner.nextInt();
                TournamentSelection.setDefaultTournamentSize(tournamentSize);
                break;

            case "RS":
                sSelection = SelectionFactory.SelectionType.RANK_SELECTION;
                break;

            case "Rand":
                sSelection = SelectionFactory.SelectionType.RANDOM_SELECTION;
                break;

            default:
                sSelection = SelectionFactory.SelectionType.FITNESS_PROPORTIONATE_SELECTION;
                break;
        }

        clearShell();
    }

    private static void adjustCrossover() {
        System.out.println("Crossover:");
        Scanner scanner = new Scanner(System.in);

        System.out.print("CrossoverType: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "OPC":
                sCrossover = CrossoverFactory.CrossoverType.ONE_POINT_CROSSOVER;
                break;

            case "MPC":
                sCrossover = CrossoverFactory.CrossoverType.MULTI_POINT_CROSSOVER;
                System.out.print("CrossoverCount: ");
                int crossoverCount = scanner.nextInt();
                MultiPointCrossover.setDefaultCrossoverCount(crossoverCount);
                break;

            case "UC":
                sCrossover = CrossoverFactory.CrossoverType.UNIFORM_CROSSOVER;
                System.out.print("UniformRate: ");
                double uniformRate = scanner.nextDouble();
                UniformCrossover.setDefaultUniformRate(uniformRate);
                break;

            default:
                sCrossover = CrossoverFactory.CrossoverType.ONE_POINT_CROSSOVER;
                break;
        }

        clearShell();
    }

    private static void adjustMutation() {
        System.out.println("Mutation:");
        Scanner scanner = new Scanner(System.in);

        System.out.print("MutationType: ");
        String choice = scanner.nextLine();

        System.out.print("MutationRate: ");
        double mutationRate = scanner.nextDouble();

        switch (choice) {
            case "RRM":
                sMutation = MutationFactory.MutationType.RANDOM_RESETTING_MUTATION;
                RandomResettingMutation.setDefaultMutationRate(mutationRate);
                break;

            case "SWM":
                sMutation = MutationFactory.MutationType.SWAP_MUTATION;
                SwapMutation.setDefaultMutationRate(mutationRate);
                break;

            case "SM":
                sMutation = MutationFactory.MutationType.SCRAMBLE_MUTATION;
                ScrambleMutation.setDefaultMutationRate(mutationRate);
                break;

            case "IM":
                sMutation = MutationFactory.MutationType.INVERSION_MUTATION;
                InversionMutation.setDefaultMutationRate(mutationRate);
                break;

            default:
                sMutation = MutationFactory.MutationType.RANDOM_RESETTING_MUTATION;
                break;
        }

        clearShell();
    }

    private static void adjustEvolution() {
        System.out.println("Evolution:");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Elitism: ");
        sElitism = scanner.nextBoolean();

        clearShell();
    }

    private static void clearShell() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
